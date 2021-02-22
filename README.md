# java-competition-manager
Skład zespołu:Jakub Szwajcok, Miłosz Wodzisz, Tomasz Sowa

Opis:
Projekt do zarzadzania baza danych graczy i druzyn oraz tworznieniem zawodów i zapisywaniem zwycienstw i przegranych. Gdy włączymy aplikacje z caly com.competition.Main.Main zobaczymy strone główna wraz z menu naszego programu. Po lewej znajduja sie 3 przyciski. Pierwszy odpowiada za wyswietlanie rankingu graczy i druzyn. Przy pierwszym uruchomieniu widzimy ten ranking automatycznie. Nastepnym guzikiem mozemy przejsc do tworzenia zawodow. Po kliknieciu pojawia sie wybor na ile graczy chcemy utworzyc turniej. Gdy wybierzemy ilosc graczy pojawia sie okno w ktorym wpisujemy od gory po kolei nazwy druzyn ktore maja brac udzial w zawodach. Jest to 16 pol jednak wypelniamy tylko tyle ile potrzebujey czyli na przyklad jezeli wybralismy turniej dla 2 druzyn wpisujemy tylko 2 druzyny. Po dodaniu druzyn pojawia sie 'bracket' z uzupelnionymi juz druzynami gdzie mozemy wpisywac wyniki. Po wpisaniu wyników z poszczegolnych meczow klikamy przycisk zatwierdz wynik. Gdy wylonimy juz zwyciezce mozemy wrocic do menu poprzez przycisk zakoncz. W menu bedziemy mogli zobaczyc zmienione wyniki dla druzyn poniewaz kazdy mecz przypisal druzynie odpowiednio zwyciestwo lub przegrana. Ostatnim guzikiem w menu jest edycja i dodawanie graczy. Po kliknieciu w niego zobacyzmy panel ze wszystkimi graczami dostepnymi i ich danymi. U gory mozemy zobaczyc 3 przyciski ktore tworza, edytuja lub usuwaja gracza odpowiednio. Po przycisnieciu dodaj lub edytuj wyskoczy dodatkowe okienko gdzie uzupelniamy odpowiednio dane i klikamy zapisz. Przy edycji musimy sie upewnic ze wybralismy record z tabeli ktory mamy edytowac. Pola beda automatycznie uzupelnione co ulatwia zmiane danyc. Po wybraniu rekordu z tabeli i wcisnieciu usun pojawi sie okno upewniajace sie czy chcemy usunac danego gracza. Po wscisnieciu tak gracz zostaje usuniety. Przy kazdej z tych operacji tabela zostaje odswiezona jednak mozemy to tez zrobic manualnie przyciskiem odswiez. Dodany on zostal po to aby mozna bylo odczytac informacje ktore zostaly zmienione w bazie danych np poprzez inna instancje aplikacji.

Narzedzia:
Projekt jest Mavenowy i poprawnie buduje się z linii komend konsoli systemu operacyjnego
Przykłady testów jednostkowych mozna znaleźć w klasie: com.competition.JSONDataBase.PlayerRankingTest. Są to testy jednostkowe do klasy: com.competition.JSONDataBase.PlayerRanking
Projekt budowany jest przez Maven, wykonywalny jar możliwy do uruchomienia z konsoli systemu operacyjnego

Design:
Przykład klasy abstrakcyjnej: com.competition.JSONDataBase.PlayerRanking.PlayerComponent
Przykład polimorfizmu: w klasie: com.competition.JSONDataBase.PlayerRanking mozna znaleźć ArrayList<PlayerComponent> listOfPlayersTeams. Sa tam umieszczane obiekty typu com.competition.JSONDataBase.PlayerRanking.Team oraz  com.competition.JSONDataBase.PlayerRanking.PlayerData, które dziedzicza po klasie abstrakcyjnej: com.competition.JSONDataBase.PlayerRanking.PlayerComponent

Wykorzystane wzorce projektowe: 
Builder,przykład w klasach: com.competition.JSONDataBase.PlayerRanking.PlayerData.PlayerData, com.competition.JSONDataBase.PlayerRanking.PlayerData.Name
Composite Design,przyklad w klasach: com.competition.JSONDataBase.PlayerRanking.Team, która dziedziczy po tej samej klasie co com.competition.JSONDataBase.PlayerRanking.PlayerData czyli : com.competition.JSONDataBase.PlayerRanking.PlayerComponent. W klasie com.competition.JSONDataBase.PlayerRanking znajduje sie lista: ArrayList<PlayerComponent> listOfPlayersTeams, dzieki której gracze mogą być przechowywani samodzielnie bez drużyny albo być zawarci w drużynie - osobnej klasie, która jest kontenerem dla graczy.
Iterator, przyklad w klasach: com.competition.MainPage.RankingModel 

Wykorzystane wzorce architektoniczne:
MVC: Przykład klasy: com.competition.JSONDataBase.PlayerRanking
Przykład: com.competition.MainPage controller:MainPageController view:MainPage.fxml/Ranking.fxml model:RankingModel
Przykład: com.competition.Player controller:PlayerController view:PlayerIndex.fxml model:com.competition.JSONDataBase.PlayerRanking.PlayerDataWithTeam


