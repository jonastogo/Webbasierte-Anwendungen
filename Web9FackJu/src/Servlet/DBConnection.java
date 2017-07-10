/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgresql.util.PSQLException;

import Stuff.tableHead;
import TBL.Data;
import TBL.TblLocation;
import TBL.TblObservedObject;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 *
 * @author Jonas-Lampe
 */
@WebServlet(name = "DBConnection", urlPatterns = {"/DBConnection"})
public class DBConnection extends HttpServlet {

    final String JDBC_DRIVER = "org.postgresql.Driver";  
    final String DB_URL = "jdbc:postgresql://scl2-ifm-min.ad.fh-bielefeld.de/scltest";
    final String USER = "wba2017";
    final String PASS = "wba2017";
    
    Statement stmt = null;
    Connection conn = null;
    boolean firstCall = true;
    
    ArrayList<TblObservedObject> tblObservedObject = new ArrayList<TblObservedObject>();
    ArrayList<TblLocation> tblLocation = new ArrayList<TblLocation>();
    ArrayList<tableHead> heads = new ArrayList<tableHead>();
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>-
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String anlageid = request.getParameter("anlageid");
		 
		try( PrintWriter out = response.getWriter()){
			Class.forName(JDBC_DRIVER);
			    
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			    
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<script type=\"text/javascript\" src=\"" + request.getContextPath() + "/js/bootstrap.min.js\"></script>");
			out.println("<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/bootstrap.min.css\" type=\"text/css\"/>");
			out.println("<title>WBA Praktikum 9</title>");            
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container\">");
			out.println("<div class=\"span12\">");
			if(anlageid != null){
				out.println(displayAnlage(anlageid,request));
			}else{
				out.println(displayAll());
			}
			out.println("</div>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException se2){
				try{
					if(conn!=null)
						conn.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			}
		} 
    }
    
    private String displayAnlage(String anlageid, HttpServletRequest request) {
    	try {
    		generateTBL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("<h1> Übersicht der Daten</h1>");
    	for(TblObservedObject tbloo : tblObservedObject){
		    if(tbloo.getId() == Integer.parseInt(anlageid)){
		    	sb.append("ID: " + tbloo.getId() + "<br>")
		    	.append("Name: " + tbloo.getName() + "<br>")
		    	.append("Location:");
		    	if(tbloo.getLocation() != null){
		    		sb.append("<a href=\"https://www.google.de/maps/@" + tbloo.getLocation().getLatitude()+ "," + tbloo.getLocation().getLongitude()+",18z\">")
		    		.append(tbloo.getLocation().getName() + "</a><br>");
		    	}
		    	else {
		    		sb.append(" nicht angegeben<br>");
		    	}
		    	if(tbloo.getTable() != null && tbloo.getTable().size() > 0){
		    		sb.append("<h1> Daten-Tabelle </h1>");
		    		sb.append("<table border=\"1\">");
		    		for(tableHead th : heads){
		    			if(th.getId()==tbloo.getId()){
		    				sb.append("<thead><tr>");
		    				for(String col : th.getColNames()){
		    					sb.append("<th>" + col + "</th>");
		    				}
		    				sb.append("</tr></thead>");
		    			}
		    		}
			        for(Data d : tbloo.getTable()){
			        	sb.append("<tr>");
			        	for(String s : d.getData()){
			        		if(s != null){
				        		try{
				        			float f = Float.parseFloat(s);
				        			DecimalFormat df = new DecimalFormat("#.#####");
				        			String val = df.format(f);
				        			sb.append("<td>" + val + "</td>");
				        		}
				        		catch(NumberFormatException e){
				        			sb.append("<td>" + s + "</td>");
				        		}
			        		}
			        		else{
			        			sb.append("<td>NULL</td>");
			        		}
			        	}
			        	sb.append("</tr>");
			        }
			        sb.append("</table><br>");
			    }
		    }
		}	
		sb.append("<h1> Übersicht der Anlagen </h1>");
		for(TblObservedObject tbloo2 : tblObservedObject){
		    if(tbloo2.getParent() == Integer.parseInt(anlageid)){
		    	sb.append("<a href=\"?anlageid=")
	        	.append(tbloo2.getId())
	        	.append("\">")
	        	.append(tbloo2.getName())
	        	.append("</a><br>");
		    }
		}
		
		return sb.toString();
	}
    
    public String displayAll(){
    	
    	try {
    		generateTBL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		StringBuilder sb = new StringBuilder();
		sb.append("<h1> Übersicht der Anlagen </h1>");
		for(TblObservedObject tbloo : tblObservedObject){
		    if(tbloo.getParent() == 0){
		        sb.append("<a href=\"?anlageid=")
		        	.append(tbloo.getId())
		        	.append("\">")
		        	.append(tbloo.getName())
		        	.append("</a><br>");
		     	}
		 	}
		return sb.toString();
    }
    
    public void generateTBL() throws SQLException{
		if(tblObservedObject.size() == 0 && tblLocation.size() == 0){
	    	String sqlLocation = "SELECT id, name, street, housenumber, postcode, floor, longitude, latitude, city FROM smartmonitoring.tbl_location";
	        ResultSet rsLocation = stmt.executeQuery(sqlLocation);
	    	while(rsLocation.next()){
	    		tblLocation.add(new TblLocation(rsLocation.getInt("id"), rsLocation.getString("name"),
	    										rsLocation.getString("street"),rsLocation.getString("housenumber"),
	    										rsLocation.getString("postcode"),rsLocation.getString("floor"),
	    										rsLocation.getString("latitude"),rsLocation.getString("longitude"),
	    										rsLocation.getString("city")));
	    	}
	    	rsLocation.close();
	    	
	        String sql = "SELECT id, name, location_id, parent_id FROM smartmonitoring.tbl_observedobject";
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while(rs.next()){
	        	int id = rs.getInt("id");
	        	String name = rs.getString("name");
	        	int location = 0;
	        	location = rs.getInt("location_id");
	        	int parent = 0;
	        	parent = rs.getInt("parent_id");
	        	TblLocation tblL = null;
	        	for(TblLocation l : tblLocation){
	        		if(l.getId() == location){
	        			tblL = l;
	        		}
	        	}
	        	if(tblL != null)
	        		tblObservedObject.add(new TblObservedObject(id, name, tblL, parent, new ArrayList<Data>()));
	        	else
	        		tblObservedObject.add(new TblObservedObject(id, name, null, parent, new ArrayList<Data>()));
	        }
	        rs.close();
	        
	        for(TblObservedObject tbl : tblObservedObject){
	        	try{
	        		String sqlData = "SELECT * FROM smartmonitoring.data_" + tbl.getId() + " LIMIT 10";
			        ResultSet rsData = stmt.executeQuery(sqlData);
			        ResultSetMetaData md = rsData.getMetaData();
			        int col = md.getColumnCount();
			        ArrayList<String> dataCol = new ArrayList<String>();
			        ArrayList<String> allCol = new ArrayList<String>();
			        ArrayList<Data> data = new ArrayList<Data>();
			        for (int i = 1; i <= col; i++){
			        	String col_name = md.getColumnName(i);
			        	dataCol.add(col_name);
			        }
			        allCol.addAll(dataCol);
			        heads.add(new tableHead(allCol, tbl.getId()));
			    	while(rsData.next()){
			    		Data d = new Data(new ArrayList<String>());
			    		for(String column : dataCol){
			    			d.getData().add(rsData.getString(column));
			    		}
			    		data.add(d);
			    	}
			    	rsData.close();
			    	tbl.setTable(data);
		    	}
	        	catch(PSQLException e){
	        	}
	        }
		}
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }

    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
