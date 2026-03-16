@pruebas
Feature: La barra de navegacion me permite navegar a todas las subsecciones
    to se the pages
    without logging in
    i can click the navegation bar

 Background: I am on the free range testers web without logging in.
    Given I navigate to freerangetesters.com

@caso1    
Scenario Outline: i can acces the subpages through the navegacion bar  
    When I go to <section> using the navigation bar
    Examples:
        | section| 
        | Academia |  
        | Cursos |  
        | Mentorías |
        | Talleres |
@caso2
Scenario: courses are pressented correctly to potential customers
    When I go to Cursos using the navigation bar
    And I Select Introducción al Testing de Software

#caso para picklist 
@caso3
Scenario: resources are pressented correctly to potential customers2
    When I select Recursos
    And I select Automation Sandbox
    Then I can validate the options in the dropdown
    