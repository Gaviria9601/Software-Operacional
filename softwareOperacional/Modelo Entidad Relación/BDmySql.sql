use softwareoperacional;
create table TipoUsuario(
id integer PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(30),
descripcion varchar(2000)
);

use softwareoperacional;
create table TipoProducto(
id integer PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(30)
);

use softwareoperacional;
create table Area(
id integer PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(30),
descripcion varchar(2000)
);
use softwareoperacional;
create table Departamento(
id integer PRIMARY KEY AUTO_INCREMENT,
nombre varchar(20)
);
use softwareoperacional;
create table Municipio(
id integer PRIMARY KEY AUTO_INCREMENT,
nombre varchar(20),
departamento integer,
FOREIGN KEY (departamento) REFERENCES Departamento(id)
);
use softwareoperacional;
create table Cargo(
id integer PRIMARY KEY AUTO_INCREMENT,
cargo varchar(30),
salario int,
area integer,
FOREIGN KEY (area) REFERENCES Area(id)
);
use softwareoperacional;
create table Usuario(
id integer PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(15),
contrasenia varchar(8),
tipo integer,
FOREIGN KEY (tipo) references TipoUsuario(id)
);
use softwareoperacional;
create table Empleado(
codigo integer PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(30),
apellido VARCHAR(30),
fechaNacimiento DATE,
fechaIngresoEmpresa DATE,
cedula VARCHAR(10),
genero VARCHAR(1),
municipio integer,
usuario integer,
cargo integer,
FOREIGN KEY (municipio) REFERENCES Municipio(id),
FOREIGN KEY (usuario) REFERENCES Usuario(id),
FOREIGN KEY (cargo) REFERENCES Cargo(id)
 ); 
use softwareoperacional;
create table Producto(
codigo integer PRIMARY KEY AUTO_INCREMENT,
nombre varchar(30),
fechaIngreso date,
descripcion VARCHAR(2000),
cantidad int,
codigoLote varchar(20),
peso int,
dimensiones varchar(30),
valor int,
empleado int,
tipoproducto int,
FOREIGN KEY (empleado) REFERENCES Empleado(codigo),
FOREIGN KEY (tipoProducto) REFERENCES TipoProducto(id)
);
use softwareoperacional;
create table Cliente(
codigo integer PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(30),
apellido VARCHAR(30),
fechaNacimiento DATE,
cedula VARCHAR(10),
genero VARCHAR(1),
municipioNacimiento integer,
FOREIGN KEY (municipioNacimiento) REFERENCES Municipio(id)
 ); 
use softwareoperacional;
create table Venta(
codigo integer PRIMARY KEY AUTO_INCREMENT,
fecha date,
totalVenta int,
vendedor integer,
cliente integer,
FOREIGN KEY (vendedor) REFERENCES Empleado(codigo),
FOREIGN KEY(cliente) REFERENCES Cliente(codigo)
);
use softwareoperacional;
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
use softwareoperacional;
create table Auditoria(
codigo integer PRIMARY KEY AUTO_INCREMENT,
fechaHora date,
ingreso varchar(50),
origen varchar(30),
navegador varchar(30),
accion varchar(30),
registroRealizoAccion varchar(30),
usuario integer,
FOREIGN KEY (usuario) REFERENCES Usuario(id)
);
