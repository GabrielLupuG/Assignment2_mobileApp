//Gabriel Lupu c15712195 DT354/year4
package app.gabriel.assignment2_mobileApp;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.io.File;
import java.util.ArrayList;

public class Repository
{
    private Context context;
    private SQLiteDatabase db;
    private  String dbpath;

    public Repository(Context context)
    {
        this.context = context;
        dbpath = new ContextWrapper(context).getFilesDir().getAbsolutePath();
        db = SQLiteDatabase.openOrCreateDatabase(dbpath+ File.separator+"database.db", null);
        createDBIfNotExists();
    }

    private void createDBIfNotExists()
    {
        db.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS Friend(name Varchar NOT NULL PRIMARY KEY,phone Varchar,email Varchar,constraint name_unique unique(name, phone));";
        SQLiteStatement insert = db.compileStatement(sql);
        insert.execute();
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public boolean insertFriend(Friend f)
    {
        try
        {
            db.beginTransaction();
            String sql = "INSERT INTO Friend VALUES(?,?,?);";
            SQLiteStatement insert = db.compileStatement(sql);
            insert.bindString(1, f.getName());
            insert.bindString(2, f.getPhoneNumber());
            insert.bindString(3, f.getEmail());
            insert.execute();
            db.setTransactionSuccessful();
            db.endTransaction();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    public Friend getFriend(String searchTag)
    {
        Cursor cursor = db.rawQuery("SELECT * FROM Friend WHERE Name LIKE '"+searchTag+"' OR phone LIKE '"+searchTag+"';",null);
        if (cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                String friendName = cursor.getString(0);
                String friendPhone = cursor.getString(1);
                String friendMail = cursor.getString(2);
                if (friendName.equals(searchTag) || friendPhone.equals(searchTag))
                {
                    Friend toReturn = new Friend();
                    toReturn.setName(friendName);
                    toReturn.setPhoneNumber(friendPhone);
                    toReturn.setEmail(friendMail);
                    return toReturn;
                }
                cursor.moveToNext();
            }
        }
        return null;
    }

    public ArrayList<Friend> getAllFriends()
    {
        ArrayList<Friend> friendsList = new ArrayList<Friend>();
        Cursor cursor = db.rawQuery("SELECT * FROM Friend;",null);
        if (cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                String friendName = cursor.getString(0);
                String friendPhone = cursor.getString(1);
                String friendMail = cursor.getString(2);
                Friend f = new Friend();

                f.setName(friendName);
                f.setPhoneNumber(friendPhone);
                f.setEmail(friendMail);
                friendsList.add(f);

                cursor.moveToNext();
            }
        }
        return friendsList;
    }

    public boolean updateFriend(String oldFriendName,Friend newFriendEntity)
    {
        try
        {
            db.beginTransaction();
            String sql = "UPDATE Friend SET name=?,phone=?,email=? WHERE name = ?;";
            SQLiteStatement insert = db.compileStatement(sql);
            insert.bindString(1, newFriendEntity.getName());
            insert.bindString(2, newFriendEntity.getPhoneNumber());
            insert.bindString(3, newFriendEntity.getEmail());
            insert.bindString(4,oldFriendName);
            insert.execute();
            db.setTransactionSuccessful();
            db.endTransaction();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
}
