<p align="center"><img src="https://github.com/CMZhn/Hotel_Alura_ONE_G5/blob/main/Readme_Recursos/RearmeBanner.svg") </p>

<h1 style="color:rgb(12, 160, 94)">Challenge Back End Java - Hotel Alura</h1>
<p align="left">
   <img src="https://img.shields.io/badge/ESTADO-FINALIZADO-brightgreen">
   <img src="https://img.shields.io/badge/LIBERACI%C3%93N-SEPTIEMBRE%202023-rgb(162%2C162%2C40)">
   <img src="https://img.shields.io/badge/VERSI%C3%93N-V1.0.23-rgb(15%2C128%2C193)">
   <img src="https://img.shields.io/github/repo-size/CMZhn/Hotel_Alura_ONE_G5">
</p>

Tercer reto de <a href="https://www.aluracursos.com/">Alura</a> + <a href="https://www.oracle.com/mx/education/oracle-next-education/">Oracle Next Education</a> grupo 5.
<br>
<p>
Este reto cosiste en aplicar los conocimientos adquiridos en el programa ONE sobre <strong>JAVA-JPA:Hibernate o JAVA-JDBC</strong>. En este proyecto se utilizo <strong>JAVA Maven-JPA:Hibernate</strong>, <strong>MySQL</strong>, y <strong>JAVA swing</strong>. Se aplico el cocepto <strong>CRUD</strong> (Create, Read, Update and Delete) y Consutas avanzadas mediante <strong>JPQL</strong>.
</p>
<h2>Acerca del reto</h2>
El reto cosiste en crear una aplicación de gestión de reservas con conexión a una base de datos, y que cumpla con los siguientes requisitos:
<li>
  <code>Sistema de autenticación de usuario para que solo usuarios pertenecientes al hotel consigan acceder al sistema.</Code>
 </li>
 <li> 
  <code>Permitir crear, editar y eliminar una reserva para los clientes.</Code>
  </li>
 <li>
  <code>Buscar en la base de datos todas las informaciones tanto de los clientes como de las reservas</Code>
  </li>
 <li>
  <code>Registrar, editar y eliminar datos de los huéspedes;</Code>
  </li>
 <li>
  <code>Calcular el valor de la reserva en base a la cantidades de días de la reserva.</Code>
  </li>
 <li>
  <code>Base de datos para almacenar todos los datos pedidos anteriormente.</Code>  
</li>
 <br>
 <p><strong>Extra</strong></p>
 Como extra el desafío se agregó la selección de la habitación y la vinculación de varios huéspedes por reserva según la capacidad de la habitación.

 <img src="https://github.com/CMZhn/Hotel_Alura_ONE_G5/blob/main/Readme_Recursos/AluraChanllenges.svg" height="100">

<h2>Características adicionales</h2>
<p align="left">
   <img src="https://img.shields.io/badge/CARACTER%C3%8DSTICAS%20%2B-3-brown">
</p>
Este proyecto cuenta con las siguientes características adicionales a las requeridas por el reto:
<ol>
    <li>Se creo la tabla de usuarios con su respectivo perfil en la base de datos</li>
    <li>Se incluyo la validación que de las fechas de las reservas no se entrelazaran con la reservas existentes.</li>
    <li>Se crearon validaciones de Key Typed para campos de Nombre, Telefono, y numeros</li>
</ol>
<h2>Tecnologías utilizadas</h2>
<p align="left">
   <img src="https://img.shields.io/badge/JDK-17-8A2BE2">
   <img src="https://img.shields.io/badge/JRE-17-8A2BE2">
   <img src="https://img.shields.io/badge/Maven-4.0.0-8A2BE2">
   <img src="https://img.shields.io/badge/Hibernate-5.6.14-8A2BE2">
   <img src="https://img.shields.io/badge/MySQL-8.0-8A2BE2">
   <img src="https://img.shields.io/badge/Eclipse%20IDE-2023%2006%20(4.28.0)-8A2BE2">
</p>
Para el desarrollo de este proyecto se utilizo como IDE Eclipse Versión 2023-06 con el JDK 17 (Java Development Kit 17), el proyecto fue compilado el JRE 17 (Java Runtime Environment 17), se utilizo Maven 4.0.0, se utilizo la dependencia Hibernate 5.6.14 Final, y como base de datos se utilizo MySQL 8.0.
<br>
<img src="https://github.com/CMZhn/Hotel_Alura_ONE_G5/blob/main/Readme_Recursos/java.svg" height="90"> <img src="https://github.com/CMZhn/Hotel_Alura_ONE_G5/blob/main/Readme_Recursos/EclipseLogo.svg" height="50">

<h2>Instalación</h2>
<p align="left">
   <img src="https://img.shields.io/badge/PASOS%20DE%20INSTALACI%C3%93N%20-6-orange">
</p>

Para utilizar la aplicación siga los siguientes pasos de instalación:
<ol>
    <li>Descargar e Instalar el  <strong><a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">JDK-17</a></strong>.</li>
    <li>Descargar e Instalar un IDE de su preferencia. Preferiblemente <strong><a href="https://www.eclipse.org/downloads/">IDE Eclipse</a></strong> Versión 2023-06 o superior.</li>
    <li>Descargar este repositorio en su PC e importar como proyecto Maven en su IDE.</li>
    <li>Descargar e instalar <strong><a href="https://dev.mysql.com/downloads/workbench/">MySQL Workbench 8</a></strong>.</li>
    <li>En este repositorio hay una carpeta con archivos SQL para tener la base de datos inicial, con la que se podra probar el aplicativo. <strong>IMPORTANTE</strong> Aunque Hibernate crea las tablas, hay datos minimos que debe de ser ingresados como es el caso de los usuarios, para que el aplicativo funcione. Por lo anterior es recomendable ejetutar los SQL segun su numeracion de V0 a V8.</li>
    <li>Finalmente ejecute el aplicativo desde la clase <strong>Principal</strong> en com.latam.cmz.hotelalura.</li>
</ol>


<h2>Funcionalidad</h2>


<h2>Autor</h2>
<p align="center"><strong><a href="https://github.com/CMZhn"><em>Carlos Melgar</em></a></strong></p>
<p align="center"><strong>2023</strong></p>
