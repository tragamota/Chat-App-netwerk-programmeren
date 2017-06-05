package Client.GUI;

import Server.ServerUser;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Ian on 2-6-2017.
 */
public class UsersListTable extends JTable {
    private List<ServerUser> conUsers;
    private AbstractTableModel model;

    public UsersListTable(List<ServerUser> users) {
        this.conUsers = users;
        setModel(model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return users.size();
            }

            @Override
            public int getColumnCount() {
                return 1;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return conUsers.get(rowIndex).getUserName();
            }

            @Override
            public String getColumnName(int column) {
                if(column == 0) {
                    return "Users";
                }
                return "";
            }
        });
        columnModel.getColumn(0).setPreferredWidth(40);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public void updateTable() {
        model.fireTableDataChanged();
    }
}
