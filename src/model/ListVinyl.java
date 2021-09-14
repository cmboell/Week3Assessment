/**
 *@author -Colby Boell -cmboell
 *CIS175 -Fall 2021
 *Sep 14, 2021
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="vinyls")
public class ListVinyl {
	//attributes/instance variables
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="Artist")
	private String artist;
	@Column(name="Album")
	private String album;
	@Column(name="Color")
	private String color;
	//constructors
	public ListVinyl(){//no arg default
		super();
	}
	public ListVinyl(String artist, String album, String color) {
		this.artist = artist;
		this.album = album;
		this.color = color;
	}
	//getters and setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	//methods
	public String returnVinylDetails() {
		return "Artist: " + this.artist + " : Album Name: " + this.album + " : Vinyl Color: " + this.color;
	}
}
