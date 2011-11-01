package flowweb.viewmodel
import flow.report.TransitionCount

object ProcessMap {

	def toUrlParameter(transitionCount:List[TransitionCount]) = transitionCount.map(count=>"(%s)-%s>(%s)".format(count.transition.from, count.count.toString,count.transition.to)).mkString("",",","")
	
}