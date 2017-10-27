create table TipoUsuario(
id integer PRIMARY KEY,
nombre VARCHAR2(30),
descripcion varchar2(2000)
);

create table TipoProducto(
id integer PRIMARY KEY,
nombre VARCHAR2(30)
);

create table Area(
id integer PRIMARY KEY,
nombre VARCHAR2(30),
descripcion varchar2(2000)
);

create table Departamento(
id integer PRIMARY KEY,
nombre varchar2(20)
);

create table Municipio(
id integer PRIMARY KEY,
nombre varchar2(20),
departamento integer,
FOREIGN KEY (departamento) REFERENCES Departamento(id)
);

create table Cargo(
id integer PRIMARY KEY,
cargo varchar2(30),
salario int,
area integer,
FOREIGN KEY (area) REFERENCES Area(id)
);

create table Usuario(
id integer PRIMARY KEY,
nombre VARCHAR2(15),
contrasenia varchar2(8),
tipo integer,
FOREIGN KEY (tipo) references TipoUsuario(id)
);

create table Empleado(
codigo integer PRIMARY KEY,
nombre VARCHAR2(30),
apellido VARCHAR2(30),
fechaNacimiento DATE,
fechaIngresoEmpresa DATE,
cedula VARCHAR2(10),
genero VARCHAR2(1),
municipio integer,
usuario integer,
cargo integer,
FOREIGN KEY (municipio) REFERENCES Municipio(id),
FOREIGN KEY (usuario) REFERENCES Usuario(id),
FOREIGN KEY (cargo) REFERENCES Cargo(id)
 ); 

create table Producto(
codigo integer PRIMARY KEY,
nombre varchar2(30),
fechaIngreso date,
descripcion VARCHAR2(2000),
cantidad int,
codigoLote varchar2(20),
peso varchar2(20),
dimensiones varchar2(30),
valor int,
empleado int,
tipoproducto int,
FOREIGN KEY (empleado) REFERENCES Empleado(codigo),
FOREIGN KEY (tipoProducto) REFERENCES TipoProducto(id)
);

create table Cliente(
codigo integer PRIMARY KEY,
nombre VARCHAR2(30),
apellido VARCHAR2(30),
fechaNacimiento DATE,
cedula VARCHAR2(10),
genero VARCHAR2(1),
municipioNacimiento integer,
FOREIGN KEY (municipioNacimiento) REFERENCES Municipio(id)
 ); 

create table Venta(
codigo integer PRIMARY KEY,
fecha date,
totalVenta int,
vendedor integer,
cliente integer,
FOREIGN KEY (vendedor) REFERENCES Empleado(codigo),
FOREIGN KEY(cliente) REFERENCES Cliente(codigo)
);

create table ProductoVenta(
venta integer,
producto integer,
cantidad int,
fecha date,
total int,
FOREIGN KEY (producto) REFERENCES Producto(codigo),
FOREIGN KEY (venta) REFERENCES Venta(codigo),
PRIMARY KEY (venta, producto)
);

create table Auditoria(
codigo integer PRIMARY KEY,
fechaHora date,
ingreso varchar2(50),
origen varchar2(30),
navegador varchar2(30),
accion varchar2(30),
registroRealizoAccion varchar2(30),
usuario integer,
FOREIGN KEY (usuario) REFERENCES Usuario(id)
);

CREATE SEQUENCE  "SEQ_AREA"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_AREA" before insert on area for each row
begin
:new.id := seq_area.nextval;
end;
/
ALTER TRIGGER "SEQ_AREA" ENABLE;


CREATE SEQUENCE  "SEQ_AUDITORIA"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_AUDITORIA" before insert on auditoria for each row
begin
:new.codigo := seq_auditoria.nextval;
end;
/
ALTER TRIGGER "SEQ_AUDITORIA" ENABLE;


CREATE SEQUENCE  "SEQ_CARGO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_CARGO" before insert on cargo for each row
begin
:new.id := seq_cargo.nextval;
end;
/
ALTER TRIGGER "SEQ_CARGO" ENABLE;


CREATE SEQUENCE  "SEQ_CLIENTE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_CLIENTE" before insert on cliente for each row
begin
:new.codigo := seq_cliente.nextval;
end;
/
ALTER TRIGGER "SEQ_CLIENTE" ENABLE;


CREATE SEQUENCE  "SEQ_DEPARTAMENTO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_DEPARTAMENTO" before insert  on departamento for each row
begin
:new.id := seq_departamento.nextval;
end;
/
ALTER TRIGGER "SEQ_DEPARTAMENTO" ENABLE;


CREATE SEQUENCE  "SEQ_EMPLEADO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_EMPLEADO" before insert on empleado for each row
begin
:new.codigo := seq_empleado.nextval;
end;
/
ALTER TRIGGER "SEQ_EMPLEADO" ENABLE;


CREATE SEQUENCE  "SEQ_MUNICIPIO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_MUNICIPIO" before insert on municipio for each row
begin
:new.id := seq_municipio.nextval;
end;
/
ALTER TRIGGER "SEQ_MUNICIPIO" ENABLE;


CREATE SEQUENCE  "SEQ_PRODUCTO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_PRODUCTO" before insert on producto for each row
begin
:new.codigo := seq_producto.nextval;
end;
/
ALTER TRIGGER "SEQ_PRODUCTO" ENABLE;


CREATE SEQUENCE  "SEQ_TIPOPRODUCTO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_TIPOPRODUCTO" before insert on tipoproducto for each row
begin
:new.id := seq_tipoproducto.nextval;
end;
/
ALTER TRIGGER "SEQ_TIPOPRODUCTO" ENABLE;


CREATE SEQUENCE  "SEQ_USUARIO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER "SEQ_USUARIO" before insert on usuario for each row
begin
:new.id := seq_usuario.nextval;
end;
/
ALTER TRIGGER "SEQ_USUARIO" ENABLE;


CREATE SEQUENCE  "SEQ_TIPOUSUARIO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;

CREATE OR REPLACE TRIGGER "SEQ_TIPOUSUARIO" before insert on tipousuario for each row
begin
:new.id := seq_tipousuario.nextval;
end;
/
ALTER TRIGGER "SEQ_TIPOUSUARIO" ENABLE;


CREATE SEQUENCE  "SEQ_VENTA"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;

CREATE OR REPLACE TRIGGER "SEQ_VENTA" before insert on venta for each row
begin
:new.codigo := seq_venta.nextval;
end;
/
ALTER TRIGGER "SEQ_VENTA" ENABLE;