-- CREATING DATABASE

CREATE DATABASE AccountingDb;


/* ---------- CREATING SCRIPTS TO SETUP SECURITY ----------- */
-- CREATING ROL TABLE
CREATE TABLE ROL(
                    id INT IDENTITY(1,1),
                    rol VARCHAR(200) NOT NULL,
                    PRIMARY KEY (id)
)

-- INSERT ROLES IN TABLE
INSERT INTO ROL( rol ) VALUES ('admin');
INSERT INTO ROL( rol ) VALUES ('user');

-- CREATING USERS TABLE
CREATE TABLE USERS(
                      id INT IDENTITY(1,1),
                      email VARCHAR(200) NOT NULL,
                      username VARCHAR(200) NOT NULL,
                      password VARCHAR(200) NOT NULL,
                      created DATE,
                      enable BIT,
                      idRol INT,
                      PRIMARY KEY (id),
                      FOREIGN KEY (idRol) REFERENCES ROL(id)
);

-- CREATING REFRESH TOKEN TABLE
CREATE TABLE REFRESH_TOKEN(
                              id INT IDENTITY (1,1),
                              token VARCHAR(200) NOT NULL,
                              createdate DATE ,
                              PRIMARY KEY(id)
);


-- CREATING TOKEN TABLE
CREATE TABLE TOKEN(
                      id INT IDENTITY (1,1),
                      token VARCHAR(200),
                      expiryDate DATE,
                      userid INT NOT NULL,
                      FOREIGN KEY (userid) REFERENCES Users(id),
                      PRIMARY KEY(id)
);

-- CREATING STORE PROCEDURE SAVE REFRESH TOKEN
CREATE PROCEDURE saveRefreshToken
    @Token VARCHAR(200),
    @CreateDate DATE
    AS
    INSERT INTO REFRESH_TOKEN (token, createDate) VALUES( @Token , @CreateDate )
SELECT TOP 1 * FROM REFRESH_TOKEN WHERE token = @Token ORDER BY id DESC
    GO;

-- CREATING STORE PROCEDURE CREATE USER
CREATE PROCEDURE createUser
    @Username VARCHAR(100),
    @Password VARCHAR(100),
    @Email VARCHAR(100),
    @Created DATE,
    @Enable BIT,
    @IdRol INT
    AS
    INSERT INTO USERS (username, password, email, created, enable, idRol) VALUES( @Username, @Password, @Email, @Created, @Enable, @IdRol)
    SELECT TOP 1 * FROM USERS WHERE username = @Username ORDER BY id DESC
    GO;

-- CREATING STORE PROCEDURE CREATE TOKEN
CREATE PROCEDURE createToken
    @Token VARCHAR(200),
    @Id INT
    AS
    INSERT INTO TOKEN (token, userid) VALUES( @Token, @Id)
SELECT * FROM TOKEN WHERE token = @Token
    GO;

-- CREATING STORE PROCEDURE UPDATE ENABLE USER
CREATE PROCEDURE updateEnableUser
    @Id INT,
    @Enable BIT
    AS
UPDATE USERS SET enable = @Enable WHERE id = @Id
SELECT * FROM USERS WHERE id = @Id
    GO;


/* ---------- CREATING SCRIPTS TO BUSINESS LOGIC ----------- */

-- CREATING BANK TABLE
CREATE TABLE BANK(
                     id INT IDENTITY(1,1),
                     name VARCHAR(200) NOT NULL,
                     PRIMARY KEY (id)
)

-- INSERT BANKS IN TABLE
    INSERT INTO BANK( name ) VALUES ('HSBC');
INSERT INTO BANK( name ) VALUES ('ICBC');
INSERT INTO BANK( name ) VALUES ('BBVA');
INSERT INTO BANK( name ) VALUES ('GALICIA');
INSERT INTO BANK( name ) VALUES ('SANTANDER');
INSERT INTO BANK( name ) VALUES ('NACION');
INSERT INTO BANK( name ) VALUES ('PROVINCIA');
INSERT INTO BANK( name ) VALUES ('ITAU');

-- CREATING INSPECTOR TABLE
CREATE TABLE INSPECTOR(
                          id INT IDENTITY(1,1),
                          name VARCHAR(200) NOT NULL,
                          startDate DATE NOT NULL,
                          phone VARCHAR(200) NOT NULL,
                          email VARCHAR (200) NOT NULL,
                          PRIMARY KEY (id)
)

-- CREATING CONSORCIO TABLE
CREATE TABLE CONSORCIO(
                          id INT IDENTITY(1,1),
                          name VARCHAR(200) NOT NULL,
                          cuit VARCHAR(100) NOT NULL,
                          cbu VARCHAR(100) NOT NULL,
                          email VARCHAR(200) NOT NULL,
                          idInspector INT NOT NULL,
                          idBank INT NOT NULL,
                          PRIMARY KEY (id),
                          FOREIGN KEY (idInspector) REFERENCES INSPECTOR(id),
                          FOREIGN KEY (idBank) REFERENCES BANK(id)
)

-- CREATING WORKER TABLE
CREATE TABLE WORKER(
                       id INT IDENTITY(1,1),
                       name VARCHAR(200) NOT NULL,
                       cuil VARCHAR(100) NOT NULL,
                       cbu VARCHAR(100) NOT NULL,
                       startDate DATE NOT NULL,
                       phone VARCHAR(100) NOT NULL,
                       idConsorcio INT NOT NULL,
                       idBank INT NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (idConsorcio) REFERENCES CONSORCIO(id),
                       FOREIGN KEY (idBank) REFERENCES BANK(id)
)




-- CREATING STORE PROCEDURE CREATE INSPECTOR
CREATE PROCEDURE createInspector
    @Name VARCHAR(200),
    @StartDate DATE ,
    @Phone VARCHAR(200),
    @Email VARCHAR (200)
    AS
    INSERT INTO INSPECTOR (name, startDate, phone, email) VALUES( @Name, @StartDate,@Phone,@Email)
SELECT TOP 1 * FROM INSPECTOR WHERE name = @Name ORDER BY id DESC
    GO;

-- CREATING STORE PROCEDURE UPDATE INSPECTOR BY ID
CREATE PROCEDURE updateInspectorById
    @Id INT,
    @Name VARCHAR(200),
    @StartDate DATE ,
    @Phone VARCHAR(200),
    @Email VARCHAR (200)
    AS
UPDATE INSPECTOR SET name = @Name , startDate = @StartDate , phone = @Phone , email = @Email WHERE id = @Id
SELECT TOP 1 * FROM INSPECTOR WHERE id = @Id
    GO;

-- CREATING STORE PROCEDURE CREATE CONSORCIO
CREATE PROCEDURE createConsorcio
    @Name VARCHAR(200),
    @Cuit VARCHAR(200),
    @Cbu VARCHAR(200),
    @Email VARCHAR (200),
    @IdInspector INT,
    @IdBank INT
    AS
    INSERT INTO CONSORCIO (name, cuit, cbu, email, idInspector, idBank)
    VALUES( @Name, @Cuit, @Cbu,@Email,@IdInspector,@IdBank)
SELECT TOP 1 * FROM CONSORCIO WHERE cuit = @Cuit ORDER BY id DESC
    GO;

-- CREATING STORE PROCEDURE CREATE CONSORCIO
CREATE PROCEDURE createWorker
    @Name VARCHAR(200),
    @Cuil VARCHAR(200),
    @Cbu VARCHAR(200),
    @StartDate DATE ,
    @Phone VARCHAR (200),
    @IdConsorcio INT,
    @IdBank INT
    AS
    INSERT INTO WORKER (name, cuil, cbu, startDate, phone, idConsorcio, idBank)
    VALUES( @Name, @Cuil, @Cbu,@StartDate,@Phone,@IdConsorcio, @IdBank)
    SELECT TOP 1 * FROM WORKER WHERE cuil = @Cuil ORDER BY id DESC
    GO;


