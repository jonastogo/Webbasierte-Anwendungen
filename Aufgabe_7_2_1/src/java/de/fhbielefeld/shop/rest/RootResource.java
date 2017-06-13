package de.fhbielefeld.shop.rest;

import de.fhbielefeld.shop.Database;
import de.fhbielefeld.shop.Rest_article;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Diese Resource wird nach dem Start der Applikation aufgerufen.
 * 
 * 
 * @author ffehring
 */
@Path("")
public class RootResource {
    
    @GET
    @Path("index")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String welcome() {
        
        Database.init();
        Rest_article article = new Rest_article();
        String page = "<h1>Willkommen beim FH WebShop WebService!</h1>"
                +article.availableArticles()
                +"<a href='/Aufgabe_7_2_1/cart/get_by_id?id=0'>Warenkorb ansehen</a>";
                
        
        
        
        
        String page2 =  "<h1>Willkommen beim FH WebShop WebService!</h1>"
                + "<p>"               
                + "Nutzen Sie unseren tollen WebService und verkaufen Sie für uns unsere Artikel. "
                + "</p>"
                + "Einen einzigen Artikel ansehen: <a href='/Aufgabe_7_2_1/article/get_by_id?id=1'>Ein Artikel</a>"
                + "<p>"
                + "</p>"
                + "Die Liste aller Artikel gibt es <a href='/Aufgabe_7_2_1/article/get_all'>Alle Artikel</a>"
                + "<p>"
                + "Einen neuen Artikel legen sie mit folgendem Link an: <a href='/Aufgabe_7_2_1/newarticle/'>Neuer Artikel</a>"
                + "</p>"
                + "<p>"
                + "Legen Sie einen Artikel in den Warenkoorb mit: <a href='/Aufgabe_7_2_1/cart/add?articleid=1&cartid=1'>Add Artikel</a>"
                + "</p>"
                + "<p>"
                + "Warenkorb ansehen mit: <a href='/Aufgabe_7_2_1/cart/get_by_id?id=0'>Get Cart</a>"
                + "</p>";    
        return page;
    }
    
    @GET
    @Path("newarticle")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
    public String article(){
        String page = "<form action='/Aufgabe_7_2_1/article/new'>"
                + "<lable>Name"
                + "<input name=name>"
                + "</lable>"
                + "<lable>Preis"
                + "<input name=price>"
                + "</lable>"
                + "<button>Anlegen</button>";
        
        
        return page;
    }
}
