package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Personne;
import tn.esprit.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePersonne implements IService<Personne> {
    private Connection cnx ;

    public ServicePersonne(){
        cnx = MyDatabase.getInstance().getCnx();
    }

    @Override
    public void add(Personne personne) {
        //create Qry SQL
        //execute Qry
        String qry ="INSERT INTO `personne`(`nom`, `prenom`, `age`) VALUES (?,?,?)";
        try {
            PreparedStatement pstm = cnx.prepareStatement(qry);
            pstm.setString(1,personne.getNom());
            pstm.setString(2, personne.getPrenom());
            pstm.setInt(3,personne.getAge());


            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public List<Personne> getAll() {
      //create Qry sql
        //execution
        //Mapping data


        List<Personne> personnes = new ArrayList<>();
        String qry ="SELECT * FROM `personne`";

        try {
            Statement stm = cnx.createStatement();
         ResultSet rs = stm.executeQuery(qry);

         while (rs.next()){
             Personne p = new Personne();
             p.setId(rs.getInt("id"));
             p.setNom(rs.getString(2));
             p.setPrenom(rs.getString("prenom"));
             p.setAge(rs.getInt(4));

             personnes.add(p);
         }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return personnes;
    }

    @Override
    public void update(Personne personne) {

    }

    @Override
    public void delete(Personne personne) {

    }
}
