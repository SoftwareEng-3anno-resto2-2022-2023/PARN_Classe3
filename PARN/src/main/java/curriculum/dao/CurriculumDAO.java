package curriculum.dao;

import storage.entity.*;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ConPool;
import utils.StringListUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurriculumDAO {

    public Curriculum getCurriculumByPersona(Persona persona) throws SQLException {
        int id = persona.getId();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT Soft_Skills FROM Curriculum WHERE Persona = ?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {

            String skills = resultSet.getString(1);
            List<String> listaSkills = StringListUtils.getSplittedString(skills);
            Curriculum curriculum = new Curriculum(persona, listaSkills);
            curriculum.setLingue(getLingueByCurriculum(curriculum));
            curriculum.setIstruzioni(getIstruzioniByCurriculum(curriculum));
            curriculum.setEsperienze(getEsperienzeLavorativeByCurriculum(curriculum));
            return curriculum;
        }

        return null;
    }
    public List<Curriculum> getAllCurriculum() throws SQLException {
        UtenteServiceInterface utenteServiceInterface = new UtenteService();
        List<Curriculum> listaCurriculum = new ArrayList<Curriculum>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Curriculum");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            //prendo la persona
            int id = resultSet.getInt(1);
            Persona persona = utenteServiceInterface.getPersonaById(id);
            //Prendo le soft_skills
            String skills = resultSet.getString(2);
            List<String> listaSkills = StringListUtils.getSplittedString(skills);

            Curriculum curriculum = new Curriculum(persona, listaSkills);
            curriculum.setEsperienze(getEsperienzeLavorativeByCurriculum(curriculum));
            curriculum.setIstruzioni(getIstruzioniByCurriculum(curriculum));
            curriculum.setLingue(getLingueByCurriculum(curriculum));
            listaCurriculum.add(curriculum);
        }

        return  listaCurriculum;
    }
    public List<Lingua> getLingueByCurriculum(Curriculum curriculum) throws SQLException {
        int id = curriculum.getPersona().getId();
        List<Lingua> listaLingue = new ArrayList<Lingua>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.
                prepareStatement("SELECT Nome, Livello " +
                        "FROM Lingua " +
                        "WHERE Curriculum = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Lingua lingua = new Lingua(curriculum);
            lingua.setNome(resultSet.getString(1));
            lingua.setLivello(resultSet.getString(2));

            listaLingue.add(lingua);
        }

        return listaLingue;
    }
    public List<Istruzione> getIstruzioniByCurriculum(Curriculum curriculum) throws SQLException {
        int id = curriculum.getPersona().getId();
        List<Istruzione> listaIstruzione = new ArrayList<Istruzione>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.
                prepareStatement("SELECT Tipo, Istituto, DDI, DDF, Qualifica " +
                        "FROM Istruzione " +
                        "WHERE Curriculum = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Istruzione istruzione = new Istruzione(curriculum);
            istruzione.setTipo(resultSet.getString(1));
            istruzione.setIstituto(resultSet.getString(2));

            LocalDate ddi = resultSet.getObject(3, LocalDate.class);
            istruzione.setDataInizio(ddi);

            LocalDate ddf = resultSet.getObject(4, LocalDate.class);
            istruzione.setDataInizio(ddf);

            istruzione.setQualifica(resultSet.getString(5));
            listaIstruzione.add(istruzione);
        }

        return listaIstruzione;
    }
    public List<EsperienzaLavorativa> getEsperienzeLavorativeByCurriculum(Curriculum curriculum) throws SQLException {
        int id = curriculum.getPersona().getId();
        List<EsperienzaLavorativa> listaEsperienze = new ArrayList<EsperienzaLavorativa>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.
                prepareStatement("SELECT Nome_Azienda, Tipo_Impiego, Mansioni, Datore, Contatto, Tipo_Azienda, DDI, DDF " +
                        "FROM Esperienza " +
                        "WHERE Curriculum = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            EsperienzaLavorativa esperienzaLavorativa = new EsperienzaLavorativa(curriculum);
            esperienzaLavorativa.setNomeAzienda(resultSet.getString(1));
            esperienzaLavorativa.setTipoImpiego(resultSet.getString(2));

            List<String> mansioni = StringListUtils.getSplittedString(resultSet.getString(3));
            esperienzaLavorativa.setMansioniPrincipali(mansioni);

            esperienzaLavorativa.setDatore(resultSet.getString(4));
            esperienzaLavorativa.setContatto(resultSet.getString(5));
            esperienzaLavorativa.setTipoAzienda(resultSet.getString(6));

            LocalDate ddi = resultSet.getObject(7, LocalDate.class);
            esperienzaLavorativa.setDataInizio(ddi);

            LocalDate ddf = resultSet.getObject(8, LocalDate.class);
            esperienzaLavorativa.setDataFine(ddf);
            listaEsperienze.add(esperienzaLavorativa);
        }

        return listaEsperienze;
    }
    public void addCurriculum(Curriculum curriculum) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Curriculum (Persona, Soft_Skills) VALUES (?, ?)");
        statement.setInt(1, curriculum.getPersona().getId());
        String skills = StringListUtils.getStringFromList(curriculum.getSoftSkill());
        statement.setString(2, skills);

        statement.executeUpdate();
    }
    public boolean updateCurriculum(Curriculum curriculum)  {
        Connection connection = null;
        try {
            connection = ConPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE Curriculum SET Soft_Skills = ? WHERE Persona = ?");
            //parametri set
            String skills = StringListUtils.getStringFromList(curriculum.getSoftSkill());
            statement.setString(1, skills);

            //parametri where
            statement.setInt(2, curriculum.getPersona().getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
    public void addEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Esperienza (Curriculum, Nome_Azienda, Tipo_Impiego, Mansioni, Datore, Contatto, Tipo_Azienda, DDI, DDF) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        int id = esperienzaLavorativa.getCurriculum().getPersona().getId();
        statement.setInt(1, id);
        statement.setString(2, esperienzaLavorativa.getNomeAzienda());
        statement.setString(3, esperienzaLavorativa.getTipoImpiego());
        String mansioni = StringListUtils.getStringFromList(esperienzaLavorativa.getMansioniPrincipali());
        statement.setString(4, mansioni);
        statement.setString(5, esperienzaLavorativa.getDatore());
        statement.setString(6, esperienzaLavorativa.getContatto());
        statement.setString(7, esperienzaLavorativa.getTipoAzienda());
        java.sql.Date ddi = java.sql.Date.valueOf(esperienzaLavorativa.getDataInizio());
        statement.setDate(8, ddi);
        if(esperienzaLavorativa.getDataFine() == null){
            statement.setNull(9,Types.DATE);
        } else{
            java.sql.Date ddf = java.sql.Date.valueOf(esperienzaLavorativa.getDataFine());
            statement.setDate(9, ddf);
        }

        statement.executeUpdate();
    }
    public  void removeEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Esperienza WHERE " +
                "Curriculum = ? AND Nome_Azienda = ? AND Tipo_Impiego = ?");
        statement.setInt(1, esperienzaLavorativa.getCurriculum().getPersona().getId());
        statement.setString(2, esperienzaLavorativa.getNomeAzienda());
        statement.setString(3, esperienzaLavorativa.getTipoImpiego());

        statement.executeUpdate();
    }
    public void updateEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Esperienza SET Mansioni = ?, Datore = ?, Contatto = ?, Tipo_Azienda = ?, DDI = ?, DDF = ? WHERE Curriculum = ? AND Nome_Azienda = ? AND Tipo_Impiego = ?");

        //parametri set
        String mansioni = StringListUtils.getStringFromList(esperienzaLavorativa.getMansioniPrincipali());
        statement.setString(1, mansioni);
        statement.setString(2, esperienzaLavorativa.getDatore());
        statement.setString(3, esperienzaLavorativa.getContatto());
        statement.setString(4, esperienzaLavorativa.getTipoAzienda());
        java.sql.Date ddi = java.sql.Date.valueOf(esperienzaLavorativa.getDataInizio());
        statement.setDate(5, ddi);
        if(esperienzaLavorativa.getDataFine() == null){
            statement.setNull(6,Types.DATE);
        } else{
            java.sql.Date ddf = java.sql.Date.valueOf(esperienzaLavorativa.getDataFine());
            statement.setDate(6, ddf);
        }

        //parametri where
        statement.setInt(7, esperienzaLavorativa.getCurriculum().getPersona().getId());
        statement.setString(8, esperienzaLavorativa.getNomeAzienda());
        statement.setString(9, esperienzaLavorativa.getTipoImpiego());

        statement.executeUpdate();
    }
    public void addIstruzione(Istruzione istruzione) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Istruzione (Curriculum, Tipo, Istituto, DDI, DDF, Qualifica) " +
                " VALUES (?, ?, ?, ?, ?, ?)");
        int id = istruzione.getCurriculum().getPersona().getId();
        statement.setInt(1, id);
        statement.setString(2, istruzione.getTipo());
        statement.setString(3, istruzione.getIstituto());
        java.sql.Date ddi = java.sql.Date.valueOf(istruzione.getDataInizio());
        statement.setDate(4, ddi);
        if(istruzione.getDataFine() == null){
            statement.setNull(5,Types.DATE);
        } else{
            java.sql.Date ddf = java.sql.Date.valueOf(istruzione.getDataFine());
            statement.setDate(5, ddf);
        }
        statement.setString(6, istruzione.getQualifica());

        statement.executeUpdate();
    }
    public void removeIstruzione(Istruzione istruzione) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Istruzione WHERE " +
                "Curriculum = ? AND Tipo = ? AND Istituto = ?");
        statement.setInt(1, istruzione.getCurriculum().getPersona().getId());
        statement.setString(2, istruzione.getTipo());
        statement.setString(3, istruzione.getIstituto());

        statement.executeUpdate();
    }
    public boolean updateIstruzione(Istruzione istruzione) {

        try {
            Connection connection = ConPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE Istruzione SET " +
                    "DDI = ?, DDF = ?, Qualifica = ? WHERE Curriculum = ? AND Tipo = ? AND Istituto = ?");
            //parametri set
            java.sql.Date ddi = java.sql.Date.valueOf(istruzione.getDataInizio());
            statement.setDate(1, ddi);
            if(istruzione.getDataFine() == null){
                statement.setNull(2,Types.DATE);
            } else{
                java.sql.Date ddf = java.sql.Date.valueOf(istruzione.getDataFine());
                statement.setDate(2, ddf);
            }
            statement.setString(3, istruzione.getQualifica());

            //parametri where
            statement.setInt(4, istruzione.getCurriculum().getPersona().getId());
            statement.setString(5, istruzione.getTipo());
            statement.setString(6, istruzione.getIstituto());

            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public void addLingua(Lingua lingua) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Lingua (Curriculum, Nome, Livello) VALUES (?, ?, ?)");
        int id = lingua.getCurriculum().getPersona().getId();
        statement.setInt(1, id);
        statement.setString(2, lingua.getNome());
        statement.setString(3, lingua.getLivello());

        statement.executeUpdate();
    }
    public void removeLingua(Lingua lingua) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Lingua WHERE Curriculum = ? AND Nome = ?");
        statement.setInt(1, lingua.getCurriculum().getPersona().getId());
        statement.setString(2, lingua.getNome());

        statement.executeUpdate();
    }
    public boolean updateLingua(Lingua lingua) {

        Connection connection = null;
        try {
            connection = ConPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE Lingua SET Livello = ? WHERE Curriculum = ? AND Nome = ?");

            //parametri set
            statement.setString(1, lingua.getLivello());

            //parametri where
            statement.setInt(2, lingua.getCurriculum().getPersona().getId());
            statement.setString(3, lingua.getNome());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
