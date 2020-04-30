package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Institutie;
import Model.OperaDeArtaPlastica;
import Model.SculpturaGalerieArta;
import Model.SculpturaMuzeu;
import Model.TablouGalerieArta;
import Model.TablouMuzeu;
import Model.User;

public class ServerCommunication {
	
	public List<User> getAllUsers() throws ClassNotFoundException, IOException {
		List<Object> usersObj = this.getUsers();
		List<User> users = new ArrayList<User>();
		for (Object o : usersObj) {
			if (o instanceof User) {
				users.add((User) o);
			}
		}
		return users;
	}
	
	public List<Object> getUsers() throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"get_all_users"};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		@SuppressWarnings("unchecked")
		List<Object> users = (List<Object>) inputStream.readObject();
		outputStream.close();
		inputStream.close();
		socket.close();
		
		return users;
	}
	
	public void deleteUser(User user) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"delete_user", user};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void editUser(User user, String nume, String parola) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"edit_user", user, nume, parola};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void createUser(User user) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"create_user", user};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}

	public List<Object> getInstitutii()  throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"get_all_inst"};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		@SuppressWarnings("unchecked")
		List<Object> inst = (List<Object>) inputStream.readObject();
		outputStream.close();
		inputStream.close();
		socket.close();
		
		return inst;
	}
	
	public List<Institutie> getAllInstitutii()  throws IOException, ClassNotFoundException {
		List<Institutie> inst = new ArrayList<Institutie>();
		List<Object> instObj = this.getInstitutii();
		
		for (Object o : instObj) {
			if (o instanceof Institutie) {
				inst.add((Institutie) o);
			}
		}
		
		return inst;
	}
	
	public void deleteTablouMuzeu(OperaDeArtaPlastica opera) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"delete_tablou_muzeu", opera};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void editTablouMuzeu(OperaDeArtaPlastica opera, TablouMuzeu newTablou) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"editare_tablou_muzeu", opera, newTablou};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void createTablouMuzeu(String nume, String artist, Integer anRealizare, String genPictura, String tehnica, Integer idInst) throws IOException, ClassNotFoundException{
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"creare_tablou_muzeu", nume, artist, anRealizare, genPictura, tehnica, idInst};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void deleteTablouGalerie(OperaDeArtaPlastica opera) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"delete_tablou_galerie", opera};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void editTablouGalerie(OperaDeArtaPlastica opera, TablouGalerieArta newTablou) throws ClassNotFoundException, IOException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"editare_tablou_galerie", opera, newTablou};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void createTablouGalerie(String nume, String artist, Integer anRealizare, String genPictura, String tehnica, Integer pret, Integer idInst) throws IOException, ClassNotFoundException{
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"creare_tablou_galerie", nume, artist, anRealizare, genPictura, tehnica, pret, idInst};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void deleteSculpturaMuzeu(OperaDeArtaPlastica opera) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"delete_sculptura_muzeu", opera};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void editSculpturaMuzeu(OperaDeArtaPlastica opera, SculpturaMuzeu newSculpt) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"editare_sculptura_muzeu", opera, newSculpt};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void createSculpturaMuzeu(String nume, String artist, Integer anRealizare, String tipSculptura, Integer idInst) throws IOException, ClassNotFoundException{
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"creare_sculptura_muzeu", nume, artist, anRealizare, tipSculptura, idInst};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void deleteSculpturaGalerie(OperaDeArtaPlastica opera) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"delete_sculptura_galerie", opera};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void editSculpturaGalerie(OperaDeArtaPlastica opera, SculpturaGalerieArta newSculpt) throws IOException, ClassNotFoundException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"editare_sculptura_galerie", opera, newSculpt};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void createSculpturaGalerie(String nume, String artist, Integer anRealizare, String tipSculptura, Integer pret, Integer idInst) throws IOException, ClassNotFoundException{
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"creare_sculptura_galerie", nume, artist, anRealizare, tipSculptura, pret, idInst};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
	
	public void exitServer() throws ClassNotFoundException, IOException {
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = new Socket(host.getHostName(), 9876);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		Object[] data = new Object[] {"exit"};
		outputStream.writeObject(data);
		
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		String msg = (String) inputStream.readObject();
		JOptionPane.showMessageDialog(null, msg);
		outputStream.close();
		inputStream.close();
		socket.close();
	}
}
