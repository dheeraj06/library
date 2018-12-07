package com.RestApi.Cloop;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("myresource")
public class MyResource 
{
	@POST
    @Path("add")
    @Consumes(MediaType.TEXT_PLAIN)
    public String bookAdd(@QueryParam("title") String btitle,@QueryParam("author")String bauthor,@QueryParam("category")String bcategory,@QueryParam("date")String bdate,@QueryParam("status")String bstatus )
	{
        JdbcConnection jd=new JdbcConnection();
        return jd.addBook(btitle,bauthor,bcategory,bdate,bstatus);
	}
	
	@POST
	@Path("get")
	@Produces(MediaType.TEXT_PLAIN)
	public String bookGet(@QueryParam("uid")int uid,@QueryParam("bid")int bid,@QueryParam("title") String btitle,@QueryParam("bdur")int bdur) {
		JdbcConnection jd=new JdbcConnection();
		return jd.getbook(uid, bid, btitle, bdur);
	}
	@GET
	@Path("return")
	@Produces(MediaType.TEXT_PLAIN)
	public String bookReturn(@QueryParam("uid")int uid,@QueryParam("bid")int bid) {
		JdbcConnection jd=new JdbcConnection();
		return jd.returnBook(uid,bid);
	}
}