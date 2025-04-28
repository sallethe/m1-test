package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class BankAccountWithDAOTest {

    public Connection connectionMock;

    public JdbcDAO bankDao;

    public BankAccountWithDAO bankSpy;

    public BankAccountWithDAO bankAccount;

    @BeforeEach
    void initBankAccountAndMocks() throws SQLException {
        bankAccount = new BankAccountWithDAO();
        bankDao = mock(JdbcDAO.class);
        connectionMock = mock(Connection.class);
        bankAccount.bankDao = bankDao;
        doNothing().when(connectionMock).setAutoCommit(true);
        doReturn(false).when(connectionMock).isReadOnly();
        doReturn(false).when(connectionMock).isClosed();
        doReturn(connectionMock).when(bankDao).setUpConnection();
        doNothing().when(bankDao).creerCompte();
    }

    ///  For creerCompte()
    @Test
    void shouldCreateAccountOnSuccessfulConnection() throws SQLException, ConnectException {
        // GIVEN
        // see initBankAccountAndMocks

        // WHEN
        bankAccount.creerCompte();


        // THEN
        verify(bankDao, times(1)).setUpConnection();
        verify(connectionMock, times(1)).setAutoCommit(true);
        verify(connectionMock, times(1)).isReadOnly();
        verify(connectionMock, times(1)).isClosed();
        verify(bankDao, times(1)).creerCompte();
    }

    @Test
    void shouldFailIfReadOnly() throws SQLException {
        // GIVEN
        doReturn(true).when(connectionMock).isReadOnly();

        // WHEN
        try {
            bankAccount.creerCompte();
            fail("Should throw exception");
        } catch (Exception e) {

            // THEN
            assertThat(e).isInstanceOf(ConnectException.class);
            assertThat(e.getMessage()).isEqualTo("Impossible d'avoir accès à la base de données");
            verify(bankDao, times(1)).setUpConnection();
            verify(connectionMock, times(1)).setAutoCommit(true);
            verify(connectionMock, times(1)).isReadOnly();
            verify(connectionMock, times(0)).isClosed();
            verify(bankDao, times(0)).creerCompte();
        }

    }

    @Test
    void shouldFailIfClosed() throws SQLException {
        // GIVEN
        doReturn(true).when(connectionMock).isClosed();

        // WHEN
        try {
            bankAccount.creerCompte();
            fail("Should throw exception");
        } catch (Exception e) {

            // THEN
            assertThat(e).isInstanceOf(ConnectException.class);
            assertThat(e.getMessage()).isEqualTo("Impossible d'avoir accès à la base de données");
            verify(bankDao, times(1)).setUpConnection();
            verify(connectionMock, times(1)).setAutoCommit(true);
            verify(connectionMock, times(1)).isReadOnly();
            verify(connectionMock, times(1)).isClosed();
            verify(bankDao, times(0)).creerCompte();
        }

    }

    /// For creerCompte(montant, devise)
    @Test
    void shouldCreateAccountWithParametersOnSuccessfulConnection() throws SQLException, ConnectException {

        // GIVEN
        int amount = 10;
        Devise devise = Devise.EURO;

        // WHEN
        bankAccount.creerCompte(amount, devise);

        // THEN
        verify(bankDao, times(1)).setUpConnection();
        verify(bankDao, times(1)).creerCompte(amount, devise);
    }

    @Test
    void shouldFailWithParametersOnException() throws SQLException, ConnectException {

        // GIVEN
        int amount = 10;
        Devise devise = Devise.EURO;

        doReturn(true).when(connectionMock).isReadOnly();
        doReturn(true).when(connectionMock).isClosed();

        // WHEN
        try {
            bankAccount.creerCompte(amount, devise);
            fail("Should throw exception");
        } catch (ConnectException e) {
            // THEN
            verify(bankDao, times(1)).setUpConnection();
            verify(bankDao, times(0)).creerCompte(amount, devise);
        }
    }
}