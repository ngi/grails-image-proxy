package imageproxy

import java.io.File;
import java.net.URL;

class FileBinaryCategory
{
  def static leftShift(File a_file, URL a_url)
  {
    def input
    def output

    try
    {
      input = a_url.openStream()
      output = new BufferedOutputStream(new FileOutputStream(a_file))

      output << input
    }
	catch (Exception e)
	{
		throw e
	}
    finally
    {
       input?.close()
       output?.close()
    }
  }
}
