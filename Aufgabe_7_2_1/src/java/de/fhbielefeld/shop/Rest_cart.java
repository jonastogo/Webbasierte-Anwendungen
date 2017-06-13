/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhbielefeld.shop;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Alexander
 */
@Singleton  //Sorgt dafür das sich der WebService etwas merken kann
@Path("cart")
public class Rest_cart {
    @GET
    @Path("get_by_id")
    public String getCart(@QueryParam("id") int id) {
        
        if(Database.selectCart(id) == null || Database.selectCart(id).getArticles().size()==0) {
            return "Sie haben leider noch nichts in ihrem Warenkorb";
        }
        
        String ret = "[";
        
        int i=0;
        for(Article curArticle : Database.selectCart(id).getArticles()) {
            if(i>0) {
                ret += ",";
            }
            ret += "{";
            ret += "\"name\": "+curArticle.getName() + "";
            ret += "}";
            i++;
        }
        
        ret += "]";
        ret += "<br><a href='/Aufgabe_7_2_1/cart/clear'>Clear</a>";
        return ret;
    }
    
    @GET
    @Path("new")
    public String newCart() {
        ShoppingCart sc = new ShoppingCart();
        sc = Database.updateCart(sc);
        
        return "{\"id\": "+sc.getId()+"}";
    }
    
    @GET
    @Path("add")
    public void  /*String*/ addToCart(
            @QueryParam("articleid") int articleid,
            @QueryParam("cartid") int cartid) {

        // Create new cart if not exists
        if(Database.selectCart(cartid)==null) {
            Database.insertCart(new ShoppingCart());
        }
        
        // Get article from database and add to cart
        Article article = Database.selectArticle(articleid);
        Database.selectCart(cartid).addArticle(article);
        
        // Update cart in database
        Database.updateCart(Database.selectCart(cartid));
        
        return;// "added " + article.getName();
    }
    @GET
    @Path("clear")
    public String clearCart() {
        ShoppingCart sc =Database.selectCart(0);
        sc.clearArticle();
        Database.updateCart(sc);
        return "cleared";
    }
}
