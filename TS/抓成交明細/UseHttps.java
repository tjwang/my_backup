import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.*;
import java.security.Security;
import java.util.Properties;

public class UseHttps {
    
	public static void main(String argv[]) {
	    
		Class factoryClass = null;
		URLStreamHandlerFactory factory = null;
		String socksServer = "";
		String socksPort = "";
		String usage = "Usage: java UseHttps URL-to-be-read socksServerName(optional) socksPortNumber(optional)";
		
		if ((argv == null) || (argv.length == 0)) {
		    System.out.println(usage);
		    return;
		}
		
		String prefix = "https://";
		// Build the complete URL, including the protocol
		String fullURL = prefix.concat(argv[0]);
		
		if (argv.length > 3) {
    	    System.out.println(usage);
    	    return;
        }
        
		if (argv.length >= 2) {        		    
		    socksServer = argv[1];
			if (argv.length == 3) {
		            socksPort = argv[2];
		    }
		}
		
		System.out.println("Computed URL is " + fullURL);
		
		// Now either we can rely on the user to have added us to
		// the security.provider list in java.security, or we can
		// dynamically add ourselves here.  We'll set it up here.

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		
		Properties properties = System.getProperties();
		
		String handlers = System.getProperty("java.protocol.handler.pkgs");
		if (handlers == null) {
		    // nothing specified yet (expected case)
		    properties.put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		}
		else {
		    // something already there, put ourselves out front
		    properties.put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol|".concat(handlers));		    
		}
		
		if (!socksServer.equals("")) {
		    // Must do the setup to get to the socks host
		    // Could check first to see if the user already specified it on the invocation line
		    if (System.getProperty("socksProxyHost") == null) {
		        properties.put("socksProxyHost", socksServer);
		    }
		    if (!socksPort.equals("")) {
		        if (System.getProperty("socksProxyPort") == null) {
		            properties.put("socksProxyPort", socksPort);
		        }
		    }
		}
        System.setProperties(properties); // put the updated properties back in System
		
		try {
		
    		URL page = new URL(fullURL); // Process the URL far enough to find the right handler
	    	URLConnection urlc = page.openConnection();
		    urlc.setUseCaches(false); // Don't look at possibly cached data
		    System.out.println("Content-type = " + urlc.getContentType()); // See what's here
		    System.out.println("Content-length = " + urlc.getContentLength()); // See how much of it there is
		    // Read it all and print it out
    		BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	    	String buffer = "";
		    while (buffer != null) {
		        try {
		            System.out.println(buffer);
		            buffer = br.readLine();
		        }
		        catch (IOException ioe) {
		            ioe.printStackTrace();
		            break;
		        }
		    }
		}
		catch (MalformedURLException mue) {
			System.out.println(fullURL + " is not a URL that can be resolved");
		}
		catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}

