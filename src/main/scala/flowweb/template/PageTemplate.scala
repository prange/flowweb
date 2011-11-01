package flowweb.template
import PageSetupTemplate._
import ContentTemplate._
import scala.xml.NodeSeq
import flowweb.viewmodel.Tabs._
import flow.report.TransitionCount
import flowweb.viewmodel.ProcessMap
object PageTemplate {

	def defaultPage( content : ⇒ NodeSeq ) = page( "Flow - online valuechain reporting" ) {
		renderHeaderSection() ++
			renderContentSection {
				content
			} ++
			renderFooterSection()
	}

	def tabbedPage( selectedTab : String )( content : ⇒ NodeSeq ) = defaultPage {
		tabbed( tabs( selectedTab ) ) {
			content
		}
	}
	

	def bizlocMap(transitions:List[TransitionCount]) = tabbedPage( "bizloc" ) {
		val imgurl = "http://yuml.me/diagram/scruffy/activity/"+ProcessMap.toUrlParameter(transitions)
		println(imgurl)
		<img src={imgurl} style="border:1px solid silver;" />
		
	}
	
	def bizstepMap(transitions:List[TransitionCount]) = tabbedPage( "bizstep" ) {
		val imgurl = "http://yuml.me/diagram/scruffy/activity/"+ProcessMap.toUrlParameter(transitions)
		println(imgurl)
		<img src={imgurl} style="border:1px solid silver;" />
		
	}

}