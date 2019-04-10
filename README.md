# Sit-them
Application based on genetic alghoritms, which can generates sitting plan for given list of people and table set including given conditions

Po otwarciu aplikacji ukazuje się ekran główny aplikacji zawierający pięć zakładek: 

LISTA GOŚCI,GRUPY, UKŁAD, WARUNKI, USADZENIE. Po kliknięciu na nazwę danej zakładki, ładowany jest jej widok. 

Zakładka, której zawartość jest aktualnie wyświetlana jest podkreślona. Zostaną teraz kolejno omówione możliwości dostarczane przez każdą z zakładek



#LISTA GOŚCI

Gdy zakładka LISTA GOŚCI jest aktywną, na ekranie poniżej paska wyboru, znajduje się lista osób,

początkowo pusta – dodawanie osób opisano poniżej. Lista ta jest przewijana, a każdy z jej elementów

składa się z nazwy reprezentującej osobę (Rys. 1.3). Klikając i przytrzymując (ang.long click) na pozycji z

listy można ją usunąć. W prawym dolnym rogu znajduje się przycisk z symbolem ”+”, po jego kliknięciu,

zostaje załadowany nowy widok umożliwiający dodanie osób. Użytkownik ma do dyspozycji pole tekstowe z

podpowiedzią ’nazwa gościa’ oraz dwa przyciski. Pierwszy z etykietą ’następny’ dodający gościa o wpisanej

nazwie, pozostawiający użytkownika w widoku dodawania osób, w ten sposób pozwalając od razu wprowadzić

kolejnego uczestnika spotkania. Oraz drugi z etykietą ’koniec’ dodający gościa o wpisanej nazwie, jednocześnie

przełączając z widoku dodawania osób do ekranu głównego aplikacji. Przykładowy wygląd ekranu głównego z aktywną zakładką LISTA GOŚCI został przedstawiony na Rys. 1.3, wraz z podpisami poszczególnych

elementów tego widoku.



#GRUPY

W zakładce GRUPY znajduje się przewijana lista, a każdy z jej elementów jest reprezentowany przez

nazwę danej grupy. W prawym dolnym rogu znajduje się przycisk z symbolem ”+”, po jego kliknięciu,

zostaje załadowany widok umożliwiający dodanie nowej grupy. Użytkownik ma do dyspozycji pole tekstowe

z podpowiedzią ’nazwa grupy’ oraz dwa przyciski. Pierwszy z etykietą ’następny’ dodający grupę o podanej

nazwie, pozostawiający użytkownika w widoku dodawania grup, w ten sposób pozwalając od razu dodać

kolejną grupę. Oraz drugi z etykietą ’koniec’ dodający grupę o wpisanej nazwie, jednocześnie przełączając z

widoku dodawania grup do ekranu głównego aplikacji.



#UKŁAD

Wybranie zakładki UKŁAD powoduje, że poniżej paska wyboru wyświetlana jest lista stołów. Lista ta

jest przewijana, a każdy z jej elementów składa się z nazwy reprezentującej stół. Klikając i przytrzymując

(ang. long click) na pozycji z listy można ją usunąć. W prawym dolnym rogu znajduje się przycisk z symbo-

lem ”+”, po jego kliknięciu, zostaje załadowany nowy widok, Rys. 1.3 umożliwiający dodanie stołu. Od góry

widnieją kolejno: przyciski determinujące kształt stołu – okrągły, prostokątny. Poniżej widoczny jest pro-

stokąt reprezentujący stół, na nim znajduje się pole tekstowe z podpowiedzią ńazwa stołu”umożliwiającym

użytkownikowi wpisanie nazwy stołu. Poniżej znajduje się pole tekstowe z opisem śzerokość stołu”jest to

miejsce, w które należy wpisać ilość miejsc jaka ma się pojawić obu stronach stołu. W prawym dolnym rogu

usytuowany jest przycisk z etykietą ”DODAJ”, którego naciśnięcie powoduje dodanie stołu, o ile wszystkie

parametry (kształt, nazwa, szerokość) zostały wprowadzone, oraz przeniesienie do ekranu głównego aplika-

cji.Przykładowo uzupełniony widok dodawania stołu został przedstawiony na Rys. 1.3, wraz z podpisami

poszczególnych elementów.



#WARUNKI

Gdy zakładka WARUNKI jest aktywną, na ekranie poniżej paska wyboru, znajduje się lista warunków.

Lista ta jest przewijana, a każdy z jej elementów składa się z nazwy osoby, nazwy warunku oraz trzeciej

nazwy, mogącej być reprezentacją: osoby, stołu lub grupy. Klikając i przytrzymując (ang.long click) na

pozycji z listy można ją usunąć. W prawym dolnym rogu znajduje się przycisk z symbolem ”+”, po jego

kliknięciu, zostaje załadowany nowy widok umożliwiający dodanie nowego warunku. Składa się on z trzech

etykiet, obok każdej z nich jest przycisk z opisem ’WYBIERZ’. Po naciśnięciu pierwszego, ładowana jest

lista gości, kliknięcie w jeden z jej elementów powoduje wybranie osoby – powrót do poprzedniego widoku,

oraz wstawienie nazwy osoby w etykietę obok pierwszego przycisku. Kliknięcie drugiego ładuje listę typów

warunków: MUSI OBOK, NIE MOŻE OBOK, MUSI W GRUPIE, MUSI PRZY STOLE, pozwalając wybrać

jeden analogicznie jak w przypadku pierwszego przycisku. Warunek MUSI OBOK jest spełniony kiedy dwie

osoby zajmują miejsce tuż obok siebie, po jednej stronie w przypadku kiedy stół jest prostokątny. Warunek

NIE MOŻE OBOK powoduje, że podane dwie osoby nie mogą zająć miejsca obok, naprzeciwko ani po ukosie,

aby był wypełniony. Wytyczna MUSI W GRUPIE determinuje, że każda osoba z danej grupy musi siedzieć w

towarzystwie – to znaczy obok lub naprzeciwko, innej osoby z tej samej grupy. Warunek MUSI PRZY STOLE

jest spełniony kiedy podana osoba siedzi przy zadanym stole. Kliknięcie trzeciego przycisku ’WYBIERZ’

ładuje listę gości/stołów/grup w zależności od wybranego wcześniej warunku, wybór jest analogiczny jak

przy wyżej opisanych akcjach. Poniżej znajduje się suwak (ang. seek bar), pozwala on ustawić priorytet

tworzonego warunku. Zakres możliwych wartości obejmuje liczby od 1 do 10. Im wyższy priorytet zostanie

nadany, tym większy wpływ ma spełnienie danego warunku na wartość funkcji celu. Przykładowy widok

dodawania warunku został przedstawiony na rysunku 1.3



#USADZENIE

Wybranie zakładki WARUNKI powoduje, że poniżej paska wyboru wyświetlana jest lista, będąca planem

przydziału miejsc, o ile taki został choć raz wygenerowany. Lista ta jest przewijana, a każdy z jej elementów

składa się z obrazka stołu, wokół którego są umieszczone liczby, będące numerami miejsc, poniżej znajduje się

lista osób, wraz z numerami miejsc które zostały im przydzielone przy danym stole. W prawym dolnym rogu

znajduje się przycisk z symbolem ”+”, po jego kliknięciu, zostanie wygenerowany nowy plan na podstawie

aktualnych danych – gości, stołów oraz warunków.





#Górne menu

Górne menu dostarcza trzech opcji: ’Import listy gości csv’, ’Import całego planu’ oraz ’Export do formatu

aplikacji’. Dwie pierwsze opcje przenoszą użytkownika do pamięci zewnętrznej telefonu pozwalając wybrać plik

– w pierwszym przypadku z rozszerzeniem csv, zaś w drugim json. Trzecia opcja pozwala na eksport zawartości aplikacji do pliku o formacie

json. Po wybraniu pliku do importu, dane z niego zostaną dołączone do listy gości w aplikacji. W przypadku importu

pliku json dane zgromadzone przez aplikację przed importem są usuwane, a na ich miejsce wstawiane są nowe.

Zaimportowanie takiego pliku powoduje wypełnienie aplikacji w każdej z zakładek identycznymi danymi jak

te, które znajdowały się w momencie eksportu w aplikacji, z której zostały wygenerowane. Opcja ’Eksport do

formatu aplikacji’ pakuje wszystkie dane aplikacji: listę gości, stołów, grup, warunków oraz plan przydziału

miejsc – jeśli został wygenerowany do pliku plan.json. Polecenie to nie powoduje usunięcia tych danych z

aplikacji, tworzy jak gdyby kopie, którą można przykładowo wysłać do współorganizatora wydarzenia.
