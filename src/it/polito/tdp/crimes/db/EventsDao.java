package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.crimes.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Integer> listAnni(){
		String sql = "select distinct year(reported_date) as anno " + 
				"from events " + 
				"order by year(reported_date)" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				
					list.add(res.getInt("anno"));
					
			
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<String> listReati(){
		String sql = "SELECT DISTINCT offense_category_id " + 
				"FROM EVENTS " + 
				"ORDER BY offense_category_id";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<String> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				
				
					list.add(res.getString("offense_category_id"));
					
			
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<String> listTipoReati(Integer anno, String categoria){
		String sql = "SELECT DISTINCT offense_type_id " + 
				"FROM EVENTS " + 
				"WHERE offense_category_id=? " + 
				"AND year(reported_date)=?";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, categoria);
			st.setInt(2, anno);
			
			List<String> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				
				
					list.add(res.getString("offense_type_id"));
					
			
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}


	public int numDistretti(Integer anno, String categoria, String vertice){
		String sql = "SELECT count(DISTINCT district_id) as cnt " + 
				"FROM EVENTS AS e1 " + 
				"WHERE e1.offense_type_id=? " + 
				"AND YEAR(e1.reported_date)=? " + 
				"AND e1.offense_category_id=?";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, vertice);
			st.setInt(2, anno);
			st.setString(3, categoria);
			
			

			int num=0;
			
			ResultSet res = st.executeQuery() ;
			
			if(res.next()) {
				
				num = res.getInt("cnt");
			
					
			
			}
			
			conn.close();
			return num ;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0 ;
		}
	}

	
}
