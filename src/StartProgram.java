/**
 *@author -Colby Boell -cmboell
 *CIS175 -Fall 2021
 *Sep 14, 2021
 */

import java.util.List;
import java.util.Scanner;

import controller.ListVinylHelper;
import model.ListVinyl;

public class StartProgram {

	/**
	 * @param args
	 */
	static Scanner in = new Scanner(System.in);
	static ListVinylHelper lvh = new ListVinylHelper();
	//used to add vinyl
	private static void addAVinyl() {
		// TODO Auto-generated method stub
		System.out.print("Enter the artist name: ");
		String artist = in.nextLine();
		System.out.print("Enter the album name: ");
		String album = in.nextLine();
		System.out.print("Enter the Vinyl Color: ");
		String color = in.nextLine();
		ListVinyl toAdd = new ListVinyl(artist, album, color);
		lvh.insertVinyl(toAdd);
	}
	//used to delete vinyl
	private static void deleteAVinyl() {
		// TODO Auto-generated method stub
		System.out.print("Enter an artist to delete: ");
		String artist = in.nextLine();
		System.out.print("Enter an album to delete: ");
		String album = in.nextLine();
		System.out.print("Enter the Vinyl color to delete: ");
		String color = in.nextLine();
		ListVinyl toDelete = new ListVinyl(artist,album,color);
		lvh.deleteVinyl(toDelete);
	}
	private static void editAVinyl() {
		// TODO Auto-generated method stub
		System.out.println("Search by Artist or Album?");
		System.out.println("1 : Search by Artist");
		System.out.println("2 : Search by Album");
		int searchBy = in.nextInt();
		in.nextLine();
		List<ListVinyl> foundVinyl;
		if (searchBy == 1) {
			System.out.print("Enter the artist's name: ");
			String artistName = in.nextLine();
			foundVinyl = lvh.searchForAlbumByArtist(artistName);
			
		} else {
			System.out.print("Enter the album name: ");
			String albumName = in.nextLine();
			foundVinyl = lvh.searchForAlbumByAlbum(albumName);

		}

		if (!foundVinyl.isEmpty()) {
			System.out.println("Found Results.");
			for (ListVinyl l : foundVinyl) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListVinyl toEdit = lvh.searchForAlbumById(idToEdit);
			System.out.println("Retrieved " + toEdit.getAlbum() + " from " + toEdit.getArtist());
			System.out.println("1 : Update Artist");
			System.out.println("2 : Update Album");
			System.out.println("3 : Update Color");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Artist: ");
				String newArtist = in.nextLine();
				toEdit.setArtist(newArtist);
			} else if (update == 2) {
				System.out.print("New Album: ");
				String newAlbum = in.nextLine();
				toEdit.setAlbum(newAlbum);
			} else if (update ==3) {
				System.out.println("New Color: ");
				String newColor = in.nextLine();
				toEdit.setColor(newColor);
			}

			lvh.updateVinyl(toEdit);

		} else {
			System.out.println("results not found");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();//calls the run menu method 

	}
    //method to display menu when main program runs
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Welcom to the vinyl collection!");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a vinyl");
			System.out.println("*  2 -- Edit a vinyl");
			System.out.println("*  3 -- Delete a vinyl");
			System.out.println("*  4 -- View the list of vinyls");
			System.out.println("*  5 -- Exit the collection");
			System.out.print("Enter Selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAVinyl();
			} else if (selection == 2) {
				editAVinyl();
			} else if (selection == 3) {
				deleteAVinyl();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lvh.cleanUp();
				System.out.println("Bye!");
				goAgain = false;
			}

		}

	}
     //method to list all vinyls
	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<ListVinyl> allVinyl = lvh.showAllVinyl();
		for(ListVinyl singleVinyl : allVinyl) {
			System.out.println(singleVinyl.returnVinylDetails());
		}
	}

}
