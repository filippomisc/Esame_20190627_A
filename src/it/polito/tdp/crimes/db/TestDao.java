package it.polito.tdp.crimes.db;


public class TestDao {

	public static void main(String[] args) {
		EventsDao dao = new EventsDao();
		
//			System.out.println(dao.listAllEvents());
			
			System.out.println(dao.listAnni().size());
			System.out.println(dao.listReati().size());
			System.out.println(dao.listTipoReati(2015, "traffic-accident").size());
			System.out.println(dao.numDistretti(2014, "other-crimes-against-persons", "assault-dv"));



	}

}
