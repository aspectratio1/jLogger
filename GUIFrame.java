import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUIFrame extends JFrame  {

    private JButton addContact;
    private JButton removeContact;
    private JButton editContact;
    private JButton edit;
    private JButton search;
    private JButton saveList;
    private JButton loadList;
    private JTextField callsignText;
    private JTextField destcallsignText;
    private JTextField gridText;
    private JTextField freqText;
    private JTextField timeText;
    private JTextField dateText;
    private JPanel buttonJPannel;
    private JPanel buttonJPannel2;
    private JPanel buttonJPannel3;


    //J List Vars
    public JScrollPane scrollPane = new JScrollPane();
    public static DefaultListModel listModel;

    //Create Labels
    private JLabel callsignLabel, destcallsignLabel, gridLabel, freqLabel, timeLabel, dateLabel;

    public GUIFrame(){

        //Create contactMan


        super("JLogger");
        contactMan conMan = new contactMan();
        buttonJPannel = new JPanel();
        buttonJPannel.setLayout(new GridLayout(1, 13));

        //Create Buttons
        addContact = new JButton("Add");
        removeContact= new JButton("Remove");
        saveList = new JButton("Save List");
        loadList = new JButton("Load List");
        search = new JButton("Search");
        edit = new JButton("Edit");


        //Create JList to hold contacts
        listModel = new DefaultListModel();
        JList contactList = new JList(listModel);
        scrollPane.setViewportView(contactList);
        add(scrollPane, BorderLayout.CENTER );

        //Add listners to buttons
        addContact.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               conMan.addContact(callsignText.getText(), destcallsignText.getText(), gridText.getText(), freqText.getText(), dateText.getText(), timeText.getText());

            }
        });


       removeContact.addActionListener(new ActionListener()
               {
                    public void actionPerformed(ActionEvent e) {
                        if (contactList.getSelectedIndex() > -1) {
                            conMan.remove(contactList.getSelectedIndex());
                        } else {
                            JOptionPane.showMessageDialog(null, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });



        saveList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                conMan.saveList();
            }
        });




        loadList.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if (conMan.contacts.size() > 0)
                        {
                            int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(null, "You will loose any unsaved contacts. Are you sure?", "Warning", dialogButton);
                            if(dialogResult == JOptionPane.YES_OPTION) {
                                final JFileChooser fc = new JFileChooser();
                                conMan.loadFileChoose();
                            }

                        }
                        else {
                            conMan.loadFileChoose();
                        }

                    }
                });

        edit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(contactList.getSelectedIndex() > -1) {
                    String[] fields = {"Callsign", "Destination Callsign", "Grid", "Freq", "Date", "Time"};
                    String field = (String) JOptionPane.showInputDialog(null,
                            "What field would you like to edit in selected row?",
                            "Edit Contact",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            fields,
                            fields[0]);
                    if (field != null) {
                        String newValue = JOptionPane.showInputDialog("Please input new value for field: " + field);
                        conMan.edit(contactList.getSelectedIndex(), field, newValue);
                    }

                }
                else{

                    JOptionPane.showMessageDialog(null, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        search.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String searchCall = JOptionPane.showInputDialog("Please input Callsign to Lookup (Not Case Sensitive)");
                conMan.search(searchCall);
            }
        });











        //Create Text Fields
        callsignText = new JTextField(6);
        destcallsignText = new JTextField(6);
        gridText = new JTextField(6);
        freqText = new JTextField(7);
        timeText = new JTextField(5);
        dateText = new JTextField(10);

        //Create Labels
        callsignLabel = new JLabel("Callsign:", SwingConstants.RIGHT);
        destcallsignLabel = new JLabel("DestCall:", SwingConstants.RIGHT);
        gridLabel = new JLabel("Grid:", SwingConstants.RIGHT);
        freqLabel = new JLabel("Freq:", SwingConstants.RIGHT);
        timeLabel = new JLabel("Time:", SwingConstants.RIGHT);
        dateLabel = new JLabel("Date:", SwingConstants.RIGHT);

        //Add Labels and buttons to grid Panel
        buttonJPannel.add(callsignLabel);
        buttonJPannel.add(callsignText);
        buttonJPannel.add(destcallsignLabel);
        buttonJPannel.add(destcallsignText);
        buttonJPannel.add(gridLabel);
        buttonJPannel.add(gridText);
        buttonJPannel.add(freqLabel);
        buttonJPannel.add(freqText);
        buttonJPannel.add(timeLabel);
        buttonJPannel.add(timeText);
        buttonJPannel.add(dateLabel);
        buttonJPannel.add(dateText);
        buttonJPannel.add(addContact);


        //Asign the J Panel to South of GUI
        add(buttonJPannel, BorderLayout.NORTH);


        //Add new Button Pannel to bottom
        buttonJPannel2 = new JPanel();
        buttonJPannel2.setLayout(new GridLayout(1, 2));
        buttonJPannel2.add(loadList);
        buttonJPannel2.add(saveList);
        add(buttonJPannel2, BorderLayout.SOUTH);


        //Add buttons ot the left
        buttonJPannel3 = new JPanel();
        buttonJPannel3.setLayout(new GridLayout(8, 1));
        buttonJPannel3.add(edit);
        buttonJPannel3.add(removeContact);
        buttonJPannel3.add(search);
        add(buttonJPannel3, BorderLayout.WEST);


    }

}
