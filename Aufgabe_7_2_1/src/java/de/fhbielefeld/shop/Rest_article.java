/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhbielefeld.shop;

import de.fhbielefeld.shop.rest.RootResource;
import javax.ejb.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Alexander
 */
@Singleton  //Sorgt dafür das sich der WebService etwas merken kann
@Path("article")
public class Rest_article {
         /**
     * Gets information about one article
     * 
     * @param id    Articles (database) id
     * @return      Article information as json
     */
    @GET
    @Path("get_by_id")
    public String article(
        @QueryParam("id") int id) {
        
        Article article = Database.selectArticle(id);
        
        String articlejson = "{";
        articlejson += "\"id\": " + article.getId() + ",";
        articlejson += "\"name\": \"" + article.getName() + "\",";
        articlejson += "\"price\": " + article.getPrice();
        articlejson += "}";
        return articlejson;
    }
      
    /**
     * Lists all available articles
     * 
     * @return json list of articles
     */
    @GET
    @Path("get_all")
    public String availableArticles() {
        String ret = "";
        int i = 0;
        for (Article curArticle : Database.selectAllArticles()) {
            
            ret+= "<a href=http://localhost:8080/Aufgabe_7_2_1/cart/add?articleid="+curArticle.getId()+"&cartid="+0+">"+curArticle.getName()+", "+curArticle.getPrice()+"</a><br>";
        }
        return ret;
    }
    
       
    /**
     * Creates an new article.
     * 
     * @param name      New articles name
     * @param price     New artiles price
     * @return          Status of creation
     */
    @GET
    @Path("new")
    public void /*String*/ newArticle(
            @QueryParam("name") String name,
            @QueryParam("price") Double price) {
        if(name==null || name.isEmpty()) {
            //return "Name vergessen!";
        }
        if(price==null) {
            //return "Preis vergessen!";
        }
        
        Article newArticle = new Article(name,price);
        Database.insertArticle(newArticle);
        RootResource root = new RootResource();
        return;// "saved! <br> <a href=http://localhost:8080/Aufgabe_7_2_1/index>back</a>";
    }
}
