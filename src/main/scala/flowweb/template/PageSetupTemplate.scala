package flowweb.template
import scala.xml.NodeSeq

object PageSetupTemplate {

	def renderHead( t : String ) = {
		<title>{ t }</title>
		<link href='http://fonts.googleapis.com/css?family=Comfortaa:700' rel='stylesheet' type='text/css'/>
		<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'/>
		<link rel="stylesheet" type="text/css" href="/style/main.css"/>
		<link rel="stylesheet" type="text/css" href="/style/content.css"/>
		<link rel="stylesheet" type="text/css" href="/style/header.css"/>
		<link rel="stylesheet" type="text/css" href="/style/footer.css"/>
		<link rel="stylesheet" type="text/css" href="/style/form.css"/>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
		<script src="/js/script.js"></script>
	}

	def renderHeaderSection() : NodeSeq = {
		<div id="header-sec">
			<div id="header">
				<div id="flowlogo"><img id="flowlogoimg" src="img/logo-notext.png"/>flow</div>
			</div>
		</div>
		<div id="headerborder"> </div>
	}

	def renderFooterSection() : NodeSeq = {
		<div id="footer-sec">
			<div id="footer-logo">
				<div id="logo">
					<div id="foursparklogo" style="float:left"><img class="logoimg" src="img/logo-notext.png"/>fourspark</div>
					<div style="float:left;padding-top:10px;padding-left:20px;"><img style="height:23px" class="logoimg" src="img/hrafn-logo.png"/></div>
				</div>
			</div>
			<div id="footer" style="clear:both"></div>
		</div>
	}

	def renderContentSection( content : ⇒ NodeSeq ) : NodeSeq = {
		<div id="content-sec">
			<div id="content">
				{ content }
			</div>
		</div>
	}

	def page( title : String )( body : ⇒ NodeSeq ) : NodeSeq = {
		<html><head>{ renderHead( title ) }</head><body>{ body }</body></html>
	}

}