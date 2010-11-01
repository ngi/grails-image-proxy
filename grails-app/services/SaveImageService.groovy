import java.io.File;

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class SaveImageService {

    static transactional = true

	def saveImage(Map attrs, String src, String name, String type) {
	   
		def location = attrs.remove('intloc')
		
		if (!location) {
			location = ConfigurationHolder.config.proxy.location
		}
		if (!location) {
			location = "web-app/images/imgProxy"
		}

		def weblocation = attrs.remove('webloc')
		if (!weblocation) {
			weblocation = ConfigurationHolder.config.proxy.weblocation
		}
		if (!weblocation) {
			weblocation = "images/imgProxy"
		}
		
		def subdir = attrs.remove('subdir')
		if (subdir) {
			location += "/" + subdir
			weblocation += "/" + subdir
		}

		def serverurl = attrs.remove('serverurl')
		if (!serverurl) {
			serverurl = ConfigurationHolder.config.grails.serverURL
		}
		if (!serverurl) {
			serverurl = "http://localhost:8080/"
		}
		
		File indir = new File(location)
		if(!indir.exists())
			indir.mkdirs()
			
		File file = new File(indir.getPath() + File.separator + name + "." + type)
		try
		{
			if (!file.exists()) {
				file.createNewFile();
				use (FileBinaryCategory)
				{
					file << src.toURL()
				}
			}
		} catch (Exception e) {
			// ignore
		}
	}
}
