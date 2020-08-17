
package com.rac021.jaxy.impl.service.voters ;

/**
 *
 * @author ryahiaoui
 */

import javax.ws.rs.GET ;
import java.io.IOException ;
import java.nio.file.Files ;
import java.nio.file.Paths ;
import javax.ws.rs.Produces ;
import javax.inject.Singleton ;
import javax.ws.rs.HeaderParam ;
import javax.ws.rs.core.Response ;
import io.quarkus.arc.Unremovable ;
import javax.annotation.PostConstruct ;
import com.rac021.jaxy.api.security.Policy ;
import com.rac021.jaxy.api.security.Secured ;
import com.rac021.jaxy.api.qualifiers.ServiceRegistry ;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author R.Yahiaoui
 */

@ServiceRegistry("voters")
@Secured(policy = Policy.Public )
@Singleton
@Unremovable
public class Service   {
   
    @ConfigProperty(name   = "votersFilePath", defaultValue = "voters.json" ) 
    String votersFilePath ;
    
    String contentVotersInJSON = "" ;
    
    @PostConstruct
    public void init() throws IOException {
    
      contentVotersInJSON = 
              new String( Files.readAllBytes( Paths.get( votersFilePath ) ) ) ;
    }

    public Service()   {
    }
    
    @GET
    @Produces( {  "xml/plain" , "json/plain" , "json/encrypted" , "xml/encrypted"   } )
    public Response cancel ( @HeaderParam("API-key-Token") String token ) throws Exception {    
        
        return Response.status( Response.Status.OK )
                       .entity( contentVotersInJSON )
                       .build() ;      
    }
}
