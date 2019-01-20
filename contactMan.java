import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class contactMan {
    private int count = 0;
    //   public contact[] contacts = new contact[1000];
    public ArrayList<contact> contacts = new ArrayList<contact>();


    ObjectOutputStream oos = null;
    FileOutputStream fout = null;
    ObjectInputStream objectinputstream = null;
    FileInputStream streamIn;


    public void contactMan() {

    }

    public void addContact(String callsign, String destCall, String grid, String freq, String date, String time) {
        if (!callsign.isEmpty() && !destCall.isEmpty() && !grid.isEmpty() && !freq.isEmpty() && !date.isEmpty() && !time.isEmpty()){
            String contactString = "Callsign: " + callsign + " Destination Callsign: " + destCall + " Grid: " + grid + " Frequency: " + freq + " Time: " + time + " Date: " + date;
            GUIFrame.listModel.addElement(contactString);


            contacts.add(new contact(callsign, destCall, grid, freq, time, date));
            //  contacts[count] =  new contact(callsign, destCall, grid, freq, time, date);
            count++;
            int itemCount = contacts.size();
            System.out.println(itemCount);

        }
            else
            JOptionPane.showMessageDialog(null, "Please input all fields", "Error", JOptionPane.ERROR_MESSAGE);


    }


    public void remove(int index) {
        if  (GUIFrame.listModel.isEmpty() == false) {
            GUIFrame.listModel.removeElementAt(index);
            contacts.get(index).equals(null);
            contacts.remove(index);
            System.out.println(index);



        }
        else {
            int dialogButton = JOptionPane.ERROR_MESSAGE;
            JOptionPane.showMessageDialog(
                    null, "No items left to remove", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void edit(int index, String field, String newValue){
        switch (field) {
            case "Callsign":
                contacts.get(index).setCall(newValue);
                break;
            case "Destination Callsign":
                contacts.get(index).setDestCall(newValue);
                break;
            case "Grid":
                contacts.get(index).setGrid(newValue);
                break;
            case "Freq":
                contacts.get(index).setFreq(newValue);
                break;
            case "Date":
                contacts.get(index).setDate(newValue);
                break;
            case "Time":
                contacts.get(index).setTime(newValue);
                break;
            default:
                break;
        }

             String newString = "Callsign: " + contacts.get(index).getCall() + " Destination Callsign: " + contacts.get(index).getDestCall() + " Grid: " + contacts.get(index).getGrid() + " Frequency: " + contacts.get(index).getFreq() + " Time: " + contacts.get(index).getTime() + " Date: " + contacts.get(index).getDate();
        GUIFrame.listModel.setElementAt(newString, index);


    }

    public void search(String callToSearch){
    Boolean found = false;
    for (int i = 0; contacts.size() > i; i ++){
        String lowecaseItem = contacts.get(i).getDestCall().toLowerCase();
        String lowercaseSearchFor = callToSearch.toLowerCase();
        if (lowecaseItem.equals(lowercaseSearchFor)){
            found = true;
            int line = i + 1;
            JOptionPane.showMessageDialog(
                    null, "This callsign exists in your list at line:" + line, "CALLSIGN FOUND", JOptionPane.PLAIN_MESSAGE);
        }

    }
    if (found == false){
        JOptionPane.showMessageDialog(
                null, "The callsign was not found in your list", "CALLSIGN NOT FOUND", JOptionPane.PLAIN_MESSAGE);


    }

    }




    public void loadFileChoose() {

        int counter = 0;
        while(!GUIFrame.listModel.isEmpty()){
            remove(0);
            counter++;
        }

        List<contact> loadedContacts = null;
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Load Contacts File");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JLogger Contacts", "dat");
        jfc.addChoosableFileFilter(filter);

                int returnValue = jfc.showDialog(null, "Load");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    System.out.println(jfc.getSelectedFile().getPath());



                    try {
                        FileInputStream fis = new FileInputStream(jfc.getSelectedFile().getPath());
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        loadedContacts = (List<contact>) ois.readObject();
                        ois.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                    for(int i = 0; i < loadedContacts.size(); i++){
                        String loadedCall = loadedContacts.get(i).getCall();
                        String loadeddestCall = loadedContacts.get(i).getDestCall();
                        String loadedGrid = loadedContacts.get(i).getGrid();
                        String loadedFreq = loadedContacts.get(i).getFreq();
                        String loadedDate = loadedContacts.get(i).getDate();
                        String loadedTime = loadedContacts.get(i).getTime();
                        addContact(loadedCall, loadeddestCall, loadedGrid, loadedFreq, loadedDate, loadedTime);

                    }

                }

            }

    public void saveList() {


        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Save Contacts File");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JLogger Contacts", "dat");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showDialog(null, "Save");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(jfc.getSelectedFile().getPath());


            try {
                Files.deleteIfExists(Paths.get(jfc.getSelectedFile().getPath()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                fout = new FileOutputStream(jfc.getSelectedFile().getPath() + ".dat", true);
                oos = new ObjectOutputStream(fout);
                oos.writeObject(contacts);
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (oos != null) {
                    // oos.close();
                }
            }

        }
    }

}

