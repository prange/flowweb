package flowweb.filter
import flow._
import data._
import flowweb._
import template._
import PageTemplate._
import scalaz._
import Scalaz._
import effects._
import unfiltered.response._
import unfiltered.request._
import com.codecommit.antixml.StAXParser
import scala.io.Source
import flow.report.TransitionCount
import flow.report.ProcessMap
import java.io.FileInputStream
import java.io.File

class FlowwebFilter extends unfiltered.filter.Plan {
	import QParams._

	val filename = "xservice 01.11.2011.xml"
	lazy val flow = loadData()

	def intent = {
		case GET( Path( "/" ) ) ⇒ Redirect( "/bizloc" )
		case GET( Path( "/bizloc" ) ) ⇒ getBizLocMap.fold( e ⇒ Ok ~> ResponseString( "An error occured" ), s ⇒ Ok ~> Html( bizlocMap( s ) ) )
		case GET( Path( "/bizstep" ) ) ⇒ getBizStepMap.fold( e ⇒ Ok ~> ResponseString( "An error occured" ), s ⇒ Ok ~> Html( bizstepMap( s ) ) )
	}

	def parseFile() = {
		val elem = new StAXParser().fromInputStream( new FileInputStream( new File( "/home/prange/heliosworkspace2/flowweb/"+filename ) ) )
		val events = elem \\ "ObjectEvent"
		events
	}

	def loadData() = {
		val events = parseFile()

		val observations = events.map( Parser.createEventList ).flatten

		val flow = new Data()

		flow.handle( AddEnrichment( WeekdayEnricher() ) ).unsafePerformIO

		flow.handle( EventObservation( observations ) ).unsafePerformIO

		flow.handle( BuildChain( ( e : Event ) ⇒ true, e ⇒ e.values.getOrElse( "epc","<unknown>" ) ) ).unsafePerformIO

		flow.handle( BuildProcess( c ⇒ true, List(), p ⇒ p ) ).unsafePerformIO

		flow
	}

	def getBizLocMap = getMap( ProcessMap.createBizLocationMap )

	def getBizStepMap = getMap( ProcessMap.createBizStepMap )

	def getMap( f : Iterable[Process] ⇒ Action[List[TransitionCount]] ) = {
		val result = flow.queryProcess( PredicateProcessQuery( e ⇒ true ) ).unsafePerformIO
		val transitions = f( result ).unsafePerformIO
		val stripped = transitions.map( l ⇒ l.map( t ⇒ TransitionCount( ProcessMap.stripPrefix( t.transition ), t.count ) ) )
		stripped

	}

	def capture( body : java.io.Reader ) : IO[Validation[String, String]] = {
		val events = Parser.parseReader( body )
		flow.handle( EventObservation( events ) )
	}
}