<h1 align="center" id="title">Proyecto FINAL Sistema de ventas en Java FX</h1>
<h5 align="center"> Programación orientada a objetos </h5>
<h6 align="center"> Equipo 12 FMAT Universidad Autonoma De Yucatán </h6>
<h1></h1>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

<p align="center">
  <img src="src/main/resources/images/shopping cart.png" />
</p>

#
# Índice
<!-- TOC -->
* [📑 Descripcion del Proyecto](#-descripcion-del-proyecto)
* [📗 Diagrama UML](#-diagrama-uml)
* [🧷 Manejo de Errores](#-manejo-de-errores)
    * [Error 1](#error-1)
    * [Error 2](#error-2)
    * [Error 3](#error-3)
* [💡 Propuesta de Mejoras](#-propuesta-de-mejoras)
    * [Mejora 1](#error-1)
    * [Mejora 2](#mejora-2)
    * [Mejora 3](#mejora-3)
    * [Mejora 4](#mejora-4)
* [📽️ Video Presentación](#-video-presentación)
#  
<!-- TOC -->

💻🎯🔒📗📚📈🧷☢️

```java
  private void clearProductFields() {
    codProduct.clear();
    productName.clear();
    price.clear();
    stock.clear();
    if (quantity.getValueFactory() != null) {
        quantity.getValueFactory().setValue(0);
    }
}
```
## [+] Descripcion del Proyecto





## [+] Diagrama UML

Use a digital tool (like Lucidchart, Draw.io, or any UML software) to create a UML diagram that represents these entities.
Ensure that the diagram is clear and accurately reflects the structure of the code.
Important: Add the UML diagram to the README file in the repository.






## [+] Manejo de Errores

### *Error 1:* 
El primer error identificado en el proyecto es que al cerrar una ventana para regresar a la amterior ,al abrir la nueva ventana, el título de esta no cambiaba y se mantenia con la anterior.<br>

<p align="center">
  <img src="src/main/resources/images/error1.1.jpg" width="30%" />
</p>
<p align="center">
  <img src="src/main/resources/images/error1.2.jpeg" width="30%" />
</p>

**Motivo del problema**<br><br>
El problema identificado que causaba el error es que al cerrar una ventana e ir a la anterior en la función <code>configureStageCloseEvent()</code> se pasaba como parametro el titulo del stage actual, es decir, al cerrar una ventana se mantenia con el titulo de la ventana anterior.
```java
  private void configureStageCloseEvent(Stage stage, String fxmlFileName, String title) {
        if (!fxmlFileName.equals(MAIN_VIEW_FXML)) {
            stage.setOnCloseRequest(e -> {
                openNewStage(getFxmlFather(fxmlFileName),title); //titulo actual
            });
        }
    }
```
**Solución**<br><br>
Para la solución del problema se creó un nuevo método en la clase <code>MenuController</code> llamado <code>getPreviousTitle()</code> para que antes de pasar como párametro el título del stage actual, mande el título del stage anterior a ese.
```java
 private String getPreviousTitle (String fxmlFileName){
        fxmlFileName = getFxmlFather(fxmlFileName);
        switch (fxmlFileName){
            case MANAGEMENT_VIEW_FXML:
                return "Menú principal";
            case PRODUCT_VIEW_FXML:
                return "Producto";
            case REPORT_VIEW_FXML:
                return "Generar reporte";
            case SALE_DETAIL_VIEW_FXML:
                return "Detalles de venta";
            case SELLER_VIEW_FXML:
                return "Menú de vendedor";
            case GENERATE_SALE_VIEW_FXML:
                return "Generar venta";
            case CUSTOMER_VIEW_FXML:
                return "Menú de cliente";
            case HELP_VIEW_FXML:
                return "Panel de Ayuda";
            default:
                return "Inicio de Sesión";
        }
    }
```
Así se obtiene el título de la ventana anterior y se corrige en la función <code>configureStageCloseEvent()</code> 
```java
 private void configureStageCloseEvent(Stage stage, String fxmlFileName) {
        if (!fxmlFileName.equals(MAIN_VIEW_FXML)) {
            stage.setOnCloseRequest(e -> {
                //obtener titulo de la ventana anterior
                String previousTitle = getPreviousTitle(fxmlFileName);
                openNewStage(getFxmlFather(fxmlFileName), previousTitle);
            });
        }
    }
```



### *Error 2:*
El segundo problema identificado dentro del programa es que al abrir alguna ventana encima de otra, es decir "emergentes", por ejemplo, los detalles de ventas, o cuando no existe algun producto y vendedor y lo deseas agregar se abren pero las dimenciones estan incorrectas siendo muy incomodas de manejar.<br><br>
**Motivo del problema**<br><br>
El motivo del problema es que el tamaño de las ventanas "emergentes" no estan bien configurados, ademas de que los titulos de las ventanas hay que ponerlos uno por uno.<br>
```java
   private void openCustomerManagementView() {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuController.class.getResource(CUSTOMER_VIEW_FXML));

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = new Stage();
        stage.setTitle("Manage Customer");
        stage.setScene(scene);
        stage.show();
    }
```
**Solución**<br><br>
Por lo que para la solución de esta problematica se decicidió aislar este tipo de ventanas ya que la clase <code>MenuController</code> como el nombre lo indica está mas enfocada al manejo de menus, que son como estas ventanas pero al cerrar no regresan a la anterior, aparte de ser no redimencionables.<br>
La Clase implementada fue <code>TabController</code> que aisla la lógica de estas ventanas por lo que con la función <code>openNewTab()</code> unicamente tienes que darle el nombre del archivo de la vista y la abre de manera correcta y segura, ademas al ser publica puedes abrir una ventana "emergente" cuando se requiera
<br>
```java
   public class TabController extends ViewFiles {

    //funcion para abrir un tab
    public static void openNewTab(String fxmlFileName){
        FXMLLoader fxmlLoaderSaleDetails = new FXMLLoader(MenuController.class.getResource(fxmlFileName));
        try {
            Scene scene = new Scene(fxmlLoaderSaleDetails.load());
            Stage stage = new Stage();
            String title = getTitle(fxmlFileName); //funcion de la clase que obtiene el titulo de la ventana
            stage.setTitle(title); 
            stage.setScene(scene);
            configureSize(fxmlFileName, stage); //funcion que configura el tamaño correcto para la ventana
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
```
### *Error 3:*



## [+] Propuesta de Mejoras
Propose Improvements
Action: Suggest enhancements for the Seller, Sales, Customer, and Product entities.
How to Do It:
Analyze each entity and think critically about how they can be improved.
Consider Object-Oriented Programming principles such as encapsulation, inheritance, and polymorphism.
Your proposals can include:
Adding new attributes or methods.
Creating new classes or interfaces.
Documentation:
Clearly outline your proposed changes in the README file.

### *Mejora 1:*


### *Mejora 2:*


### *Mejora 3:*


### *Mejora 4:*






## [+] Video Presentación
Action: Record a video showcasing your project.
How to Do It:
Use screen recording software to capture your screen.
In the video, make sure to include the following:
Introduce yourself with your full name (both students).
Show the build and run process without using any IDE.
Log in and display all available UIs in a general way.
Walk through the work you completed from point 2 to point 6.
Upload: Choose a platform to upload your video (like YouTube or Vimeo) and paste the link in the README file under a special section for it.










# Reports

