package hw.dispensary;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ACER on 10-07-2017.
 */

public class DatabaseHelper {
    Context c1c;
    dbhelper dbbbbb;
    public DatabaseHelper(Context context)
    {
        c1c=context;
        dbbbbb=new dbhelper(context);
    }

    public String admin_doctor_all_apppoint_show(String doc)
    {
        String str=new String();
        SQLiteDatabase db=dbbbbb.getWritableDatabase();
        String email=new String();
        Cursor c=db.rawQuery("SELECT * FROM doctor_table where dname='"+doc+"' ;",null);
        while (c.moveToNext())
        {
            email=c.getString(2);
        }
        Cursor c1=db.rawQuery("SELECT * FROM docapp_table where demail='"+email+"' ;",null);

        while (c1.moveToNext())
        {
            String pname=c1.getString(0);
            String date=c1.getString(1);
            str+=pname+"     "+date+"\n";
        }
        return  str;
    }


    public String admin_all_apppoint_show()
    {
        String str=new String();
        SQLiteDatabase db=dbbbbb.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM docapp_table ;",null);
        while (c.moveToNext())
        {
            String demail=c.getString(3);
            //String spec=c.getString(3);
            str+=demail+",";
        }

        String a[]= str.split(",");
        String dname,spec;
        String str1=new String();
        for(int i=0;i<a.length;i++)
        {
            Cursor c2=db.rawQuery("SELECT * FROM doctor_table where  demail='"+a[i]+"' ;",null);
            dname=new String();

            while (c2.moveToNext())
            {
                dname=c2.getString(0);
                spec=c2.getString(1);
                str1+=dname+",    "+spec;
                str1+="-";
            }
        }
        return  str1;
    }
    public boolean doctor_leave(String email,String date)
    {
        String dd = "UPDATE doctor_table SET leave='"+date+"' WHERE demail='"+email+"';" ;
        SQLiteDatabase db= dbbbbb.getWritableDatabase();
        try {
            db.execSQL(dd);
            return true;
        }
        catch (SQLException dddddddd) {
            Toast.makeText(c1c, ""+dddddddd, Toast.LENGTH_LONG).show();
        }
        return false;
    }
    public  String doct_appoint_show(String email)
    {
        String str=new String();
        SQLiteDatabase db=dbbbbb.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM docapp_table where demail='"+email+"' ;",null);
        String name,date;
        while (c.moveToNext())
        {
            name=c.getString(0);
            date=c.getString(1);
            str+=name+"      "+date;
            str+="-";
        }
        return str;
    }
   public String appoint_show(String email)
   {
       String date=new String();
       String doc_name=new String();

       SQLiteDatabase db=dbbbbb.getWritableDatabase();
       Cursor c=db.rawQuery("SELECT * FROM patapp_table where pemail='"+email+"' ;",null);
       while (c.moveToNext())
       {
           date=c.getString(0);
           doc_name=c.getString(2);

       }
       return doc_name+"       "+date;
   }
   public  boolean check_appointment_if_exist(String name,String depart,String doct,String date,String email)
   {

       SQLiteDatabase db=dbbbbb.getWritableDatabase();
       Cursor c=db.rawQuery("SELECT * FROM doctor_table where dname='"+doct+"' ;",null);
       Cursor c1=db.rawQuery("SELECT * FROM patient_table where pemail='"+email+"' ;",null);
       //Toast.makeText(c1c, ""+doct, Toast.LENGTH_SHORT).show();

       String pat_name=new String();
       while (c1.moveToNext())
       {
           pat_name=c1.getString(0);

       }
       String doc_email=new String();


       while (c.moveToNext())
       {
           String appdate=c.getString(6);
             doc_email=c.getString(2);


           if(appdate.equals(date))
           {

               return false;
           }
       }
       String pp = "INSERT INTO patapp_table(date_app,pemail,dname) VALUES ('" + date + "','" +email + "','" + doct + "' );";
       String qq = "INSERT INTO docapp_table(pname,date_app,pemail,demail) VALUES ('" + pat_name + "','" +date + "','" + email + "' ,'"+doc_email+"');";
       String rr = "INSERT INTO adminapp_table(dname,demail,special,pname,date_app,pemail) VALUES ('" + doct + "'," +
               "'" +doc_email + "','" + depart + "','" +pat_name + "','" + date + "','"+email+"' );";


       try {
           db.execSQL(pp);
           db.execSQL(qq);
           db.execSQL(rr);
       }
       catch (SQLException dd)
       {
           Toast.makeText(c1c, ""+dd, Toast.LENGTH_SHORT).show();
       }
      return  true;
   }
    public  String doctor_in_spinner(String depart)
    {
        String str1=new String();
        str1+=".....select.....-";
        SQLiteDatabase db=dbbbbb.getWritableDatabase();

        Cursor c=db.rawQuery("SELECT * FROM doctor_table WHERE special= '"+depart+"' ;",null);
        while (c.moveToNext())
        {
            String doc=c.getString(0);
            str1+=doc+"-";
        }
        //Toast.makeText(c1c, ""+depart, Toast.LENGTH_SHORT).show();
        return str1;
    }
    public  String department_in_spinner()
    {
        String str2=new String();
        str2+=".....select.....-";
        SQLiteDatabase db=dbbbbb.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * from doctor_table ;",null);
        while (c.moveToNext())
        {
            String depart=c.getString(1);
            str2+=depart+"-";
        }
        return str2;
    }
    public  String patient_Edit_data_get(String email)
    {
        String str=new String();
        SQLiteDatabase db=dbbbbb.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * from patient_table where pemail='"+email+"';",null);
        while (c.moveToNext())
        {
            String name=c.getString(0);
            String gender=c.getString(1);
            String eemail=c.getString(2);
            String state=c.getString(3);
            String pincode=c.getString(4);
            String age=c.getString(5);
            str+=name+"-"+gender+"-"+eemail+"-"+state+"-"+pincode+"-"+age;

        }
        return  str;
    }

    public  boolean patient_edit_data(String name,String gender,String email,String state,String pin,String age)
    {
        SQLiteDatabase db= dbbbbb.getWritableDatabase();

        String pp = "INSERT INTO patient_table(pname,gender,pemail,pstate,ppincode,age) VALUES ('" + name + "','" + gender + "','"
                + email + "','" + state + "','" + pin + "','" + age + "' );";

        try {
            db.execSQL("DELETE FROM patient_table where pemail='"+email+"'");
            db.execSQL(pp);
        }
        catch (SQLException dd)
        {
            Toast.makeText(c1c, ""+dd, Toast.LENGTH_SHORT).show();
        }
        return  true;
    }
    public boolean patient_registration(String name,String gender,String email,String state,String pin,String age)
    {
        String pp = "INSERT INTO patient_table(pname,gender,pemail,pstate,ppincode,age) VALUES ('" + name + "','" + gender + "','"
                + email + "','" + state + "','" + pin + "','" + age + "' );";
        SQLiteDatabase db= dbbbbb.getWritableDatabase();
        try {
            db.execSQL(pp);
            return  true;
        }
        catch (SQLException dddddddd) {
            Toast.makeText(c1c, ""+dddddddd, Toast.LENGTH_LONG).show();
        }
        return  true;
    }

    public boolean patient_login_check(String name ,String pass)
    {
        SQLiteDatabase db= dbbbbb.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * from pat_login where plemail='"+name+"';",null);
        String tablepass=new String();
        while (c.moveToNext())
        {
            tablepass=c.getString(1);
        }
        if (tablepass.equals(pass))
            return true;
        else
            return false;
    }
    public boolean doctor_login_check(String name ,String pass)
    {
        SQLiteDatabase db= dbbbbb.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * from doc_login where dlemail='"+name+"';",null);
        String tablepass=new String();
        while (c.moveToNext())
        {
            tablepass=c.getString(1);
        }
        if (tablepass.equals(pass))
            return true;
        else
            return false;
    }

        public boolean addDoctor(String name,String spec,String email,String exp,String state,String pin)
    {
        String mm =" ";
        String dd = "INSERT INTO doctor_table(dname,special,demail,exp,dstate,dpincode,leave) VALUES ('" + name+ "','" + spec + "','"
                + email + "','" + exp + "','" + state + "','" + pin + "','" + mm + "' );";

         SQLiteDatabase db= dbbbbb.getWritableDatabase();
        try {
            db.execSQL(dd);
            return  true;
        }
        catch (SQLException dddddddd) {
            Toast.makeText(c1c, ""+dddddddd, Toast.LENGTH_LONG).show();
        }
        return  false;
    }

    public String showDoctor()
    {
        SQLiteDatabase db= dbbbbb.getWritableDatabase();
        String q="SELECT * FROM doctor_table;";
        Cursor c=db.rawQuery(q,null);
        String all=new String();
         while (c.moveToNext())
         {
             String name,spec,email,exp,state,pin;


             name=c.getString(0);
             spec=c.getString(1);
             all=all+name+" "+spec;
             all+="/";
         }
        return  all;
    }
    public String showPat()
    {
        SQLiteDatabase db= dbbbbb.getWritableDatabase();
        String q="SELECT * FROM patient_table;";
        Cursor c=db.rawQuery(q,null);
        String all=new String();
        while (c.moveToNext())
        {
            String name,age,gender;


            name=c.getString(0);
            gender=c.getString(1);
            age=c.getString(5);
            all=all+name+" "+gender+" "+age;
            all+="/";
        }
        return  all;
    }


class dbhelper extends SQLiteOpenHelper {
    ///doctor
    String dname[]={"Dr SACHIT BOLA","Dr ATUL GOGIA ","Dr N.K.BHATT","Dr NEELESH SAXENA", "Dr SUNITA SHARMA","Dr AKHIL JOSHI", "Dr GEETA BANSAL", "Dr JITENDRA SINGH", "Dr SANJAY SAXENA"};


    String Special[]={"physician","cardiologists", "skin specialist","neuro surgeon"," gyanaecologists ","homopethy ","child specialists"," skin specialists ","homopethy ","ENT specialists"};

    String demail[]={"sachit@gmail.com","sachit1@gmail.com","nkb12@gmail.com ","nkbhatt000@gmail.com","neelu908@yahoomail.in"," sunitas007@hotmail.com"," joshi.akhil34567@gmail.com",
            "geetabansall1@gmail.com", "singh.jeetu@hotmail.com", "sanjay@gmail.com"};

    String Experience[]={ "8", "12","9", "15", "6"," 2"," 11", "20"," 15", "1"};

    String dstate[] ={ "Rajasthan","Rajasthan","Uttar pradesh","Delhi","Kerala","Gujrat","Haryana","Punjab","Delhi","Kerala"};

    String dpincode[]={"324002","324002","456789","678900","5567899","23455","45678","67890","34566","943218"};

    String leave[]={"","15/6/2017","","","","","","","",""};


    //patient

    String pname[]={"ARADHANA SAXENA","VIJAY KUMAR", "SADHANA SHARMA"," KESHAV BANSAL ","ISHAN JAIN ","SANJAY CHAUDARY ","DIVYANSHI AGGARWAL"," VARNIKA SINGHAL ","TUSHAR GARG"," AVIRAL GUPTA"};

    String gender[]={"F","M","F","M","M","M","F","F","M","M"};

    String pemail[]={"san@gmail.com","san1@gmail.com","sadhus34@yahoomail.in"," bansal.keshav45@gmail.com"," ishanjain67@gmail.com"," sanjay23@hotmail.com",
            "sharma.divyanshi21@gmail.com","singhal.varnika@yahoomail.in", "tusharg12@gmail.com", "aviralgupta89@hotmail.com"};

    String pstate[] ={ "Rajasthan","Rajasthan","Uttar pradesh","Delhi","Kerala","Gujrat","Haryana","Punjab","Delhi","Kerala"};

    String ppincode[]={"324002","324002","456789","678900","5567899","23455","45678","67890","34566","943218"};

    String age[]={"67","23","45","12","48","24","87","5","50","21"};



//doctor login
    String dlemail[]={"sachit@gmail.com","sachit1@gmail.com ","nkb12@gmail.com ","nkbhatt000@gmail.com","neelu908@yahoomail.in"," sunitas007@hotmail.com"," joshi.akhil34567@gmail.com",
            "geetabansall1@gmail.com", "singh.jeetu@hotmail.com", "sanjay@gmail.com"};

    String dpass[]={"sachit", "sachit1"," nkbt@3$4", "neelus&&saxena"," sharsunita&*", "bcd1596"," geetab1**","singhisking123"," rockyrachit876","sanjay"};


//patient login
    String plemail[]={"san@gmail.com"," san1@gmail.com ","sadhus34@yahoomail.in"," bansal.keshav45@gmail.com"," ishanjain67@gmail.com"," sanjay23@hotmail.com",
            "sharma.divyanshi21@gmail.com","singhal.varnika@yahoomail.in", "tusharg12@gmail.com", "aviralgupta89@hotmail.com"};


    String ppass[]={"san", "san1"," sadhu876%%"," royalkeshavb$% ","ishu^123"," sanju***ch"," diva@123"," vani****1997"," tushar123"," aviral#147"};


    String doc_table= "CREATE TABLE doctor_table (dname VARCHAR(20),special VARCHAR(20),demail VARCHAR(20),exp VARCHAR(20),dstate VARCHAR(20),dpincode VARCHAR(20),leave VARCHAR(20));";

    String pat_table= "CREATE TABLE patient_table (pname VARCHAR(20),gender VARCHAR(20),pemail VARCHAR(20),pstate VARCHAR(20),ppincode VARCHAR(20),age VARCHAR(20));";
    String pat_login= "CREATE TABLE pat_login (plemail VARCHAR(20),plpass VARCHAR(20));";
    String doc_login= "CREATE TABLE doc_login (dlemail VARCHAR(20),dlpass VARCHAR(20));";

    //patient appointment table
    String patapp_table ="CREATE TABLE patapp_table (date_app VARCHAR(20),pemail VARCHAR(20),dname VARCHAR(20));";
    //doc app table
    String docapp_table ="CREATE TABLE docapp_table (pname VARCHAR(20),date_app VARCHAR(20),pemail VARCHAR(20),demail VARCHAR(20));";
   //admin appointment table
   String adminapp_table ="CREATE TABLE adminapp_table (dname VARCHAR(20),demail VARCHAR(20),special VARCHAR(20),pname VARCHAR(20),date_app VARCHAR(20),pemail VARCHAR(20));";


    final static int Version=3;
    final  static String db_name= "prashant_shukla";
    Context context;

    public dbhelper(Context context) {
        super(context, db_name,null, Version);
        this.context=context;



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    try {
        db.execSQL(doc_table);
        db.execSQL(pat_login);
        db.execSQL(doc_login);
        db.execSQL(pat_table);
        db.execSQL(patapp_table);
        db.execSQL(docapp_table);
        db.execSQL(adminapp_table);

        for (int i = 0; i < 9; i++) {
            String dd = "INSERT INTO doctor_table(dname,special,demail,exp,dstate,dpincode,leave) VALUES ('" + dname[i] + "','" + Special[i] + "','"
                    + demail[i] + "','" + Experience[i] + "','" + dstate[i] + "','" + dpincode[i] + "','" + leave[i] + "' );";

            String pp = "INSERT INTO patient_table(pname,gender,pemail,pstate,ppincode,age) VALUES ('" + pname[i] + "','" + gender[i] + "','"
                    + pemail[i] + "','" + pstate[i] + "','" + ppincode[i] + "','" + age[i] + "' );";

            String pl = "INSERT INTO pat_login(plemail,plpass) VALUES ('" + plemail[i] + "','" + ppass[i] + "');";
            String dl = "INSERT INTO doc_login(dlemail,dlpass) VALUES ('" + dlemail[i] + "','" + dpass[i] + "');";

            db.execSQL(dd);
            db.execSQL(pp);
            db.execSQL(pl);
            db.execSQL(dl);
            Toast.makeText(context, "data inserted", Toast.LENGTH_LONG).show();
        }
    }  catch (SQLException gg){
        Toast.makeText(context, " "+gg, Toast.LENGTH_LONG).show();
    }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       onCreate(db);
    }
}
}
