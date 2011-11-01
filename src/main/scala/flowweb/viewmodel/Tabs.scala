package flowweb.viewmodel
import flowweb.template.Tab

object Tabs {

	def tabs( path : String ) = {
		List(
			Tab( "Lokasjoner", "bizloc", "Oversikt over lokasjoner", path ),
			Tab( "Steg", "bizstep", "Oversikt over steg", path ),
			Tab( "Totaltid", "elapsedtime", "Tidsforbuk mellom utvalgte punkter i verdikjeden", path ),
			Tab( "Svar", "answer", "Svar på interessant spørsmål", path ) )
	}

}