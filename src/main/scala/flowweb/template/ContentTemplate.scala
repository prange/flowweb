package flowweb.template
import scala.xml.NodeSeq

object ContentTemplate {

	def tabbed( tabs : List[Tab] )( content : ⇒ NodeSeq ) = {
		<div id="c_menu">
			<ul id="c_menu_nav">
				{ for ( tab ← tabs ) yield renderTab( tab ) }
			</ul>
		</div>
		<div id="reportpane" style="clear:both">
			{content}
		</div>

	}

	def renderTab( tab : Tab ) = {
		<li class={ if ( tab.selected ) "selected" else "" }>
			<div>
				<div><a href={ tab.href } rel={tab.caption}>{ tab.text }</a></div>
			</div>
		</li>
	}

}

case class Tab( text : String, href : String, caption : String, path : String ) {
	def selected = path contains href
}