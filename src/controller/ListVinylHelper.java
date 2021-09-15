/**
 *@author -Colby Boell -cmboell
 *CIS175 -Fall 2021
 *Sep 14, 2021
 */
package controller;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.ListVinyl;

public class ListVinylHelper {
	//global instance
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Week3Assessment");
	//method to accept vinyl to add
	public void insertVinyl(ListVinyl lv) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(lv);
		em.getTransaction().commit();
		em.close();
	}
	//method to list all vinyls
	public List<ListVinyl> showAllVinyl(){
		EntityManager em = emfactory.createEntityManager();
		List<ListVinyl> allVinyl = em.createQuery("SELECT v FROM ListVinyl v").getResultList();
		return allVinyl;
	}
	//method to delete an album
	public void deleteVinyl(ListVinyl toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListVinyl>typedQuery = em.createQuery("select lv from ListVinyl lv where lv.artist = :selectedArtist and lv.album = :selectedAlbum and lv.color = :selectedColor",ListVinyl.class);
		//substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedArtist",toDelete.getArtist());
		typedQuery.setParameter("selectedAlbum",toDelete.getAlbum());
		typedQuery.setParameter("selectedColor",toDelete.getColor());
		// only want one result
		typedQuery.setMaxResults(1);
		//get the result and save it into a new vinyl list
		ListVinyl result = typedQuery.getSingleResult();
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	//search album by artist method
	public List<ListVinyl> searchForAlbumByArtist(String artistName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListVinyl>typedQuery= em.createQuery("select lv from ListVinyl lv where lv.artist = :selectedArtist", ListVinyl.class);
		typedQuery.setParameter("selectedArtist", artistName);
		List<ListVinyl> foundAlbums = typedQuery.getResultList();
		em.close();
		return foundAlbums;
	}
	//search album by album
	public List<ListVinyl> searchForAlbumByAlbum(String albumName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListVinyl>typedQuery = em.createQuery("select lv from ListVinyl lv where lv.album = :selectedAlbum",ListVinyl.class);
		typedQuery.setParameter("selectedAlbum", albumName);
		List<ListVinyl> foundAlbums = typedQuery.getResultList();
		em.close();
		return foundAlbums;
	}
	//search for vinyl by id
	public ListVinyl searchForAlbumById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListVinyl found = em.find(ListVinyl.class, idToEdit);
		em.close();
		return found;
	}
	public void updateVinyl(ListVinyl toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	public void cleanUp() {
		emfactory.close();
	}
}
