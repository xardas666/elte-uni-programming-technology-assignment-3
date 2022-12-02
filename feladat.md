A megvalósításnak, felhasználóbarátnak és könnyen kezelhetőnek kell lennie. Törekedni
kell az objektumorientált megoldásra, de nem kötelező a többrétegű architektúra
alkalmazása.
 A megjelenítéshez első sorban elemi grafikát kell használni. Az így kirajzolt ’sprite’-ok
közül a játékoshoz tartozót billentyűzet segítségével lehessen mozgatni a jelenleg is
standard (WASD) billentyűzet kiosztásnak megfelelően. Egyéb funkciókhoz egérhez
kapcsolódó esemény vezérlőket is implementálhattok.
 Amennyiben nem algoritmussal generáltatod a játékteret, úgy legalább 10 előre definiált
játékteret készíts különböző fájlokban eltárolva. Ügyelj arra, hogy mind az algoritmussal
generált játékok esetén, illetve az előre definiált esetekben is végig játszható legyen az
adott terület.
 Minden feladathoz tartozik egy időzítő, mely a játék kezdetétől eltelt időt mutatja.
 A dokumentációnak tartalmaznia kell a választott feladat leírását, elemzését, a program
szerkezetének leírását (UML osztálydiagrammal), egy implementációs fejezetet a
kiválasztott játék szempontjából és/vagy az általad érdekesebbnek gondolt algoritmusok
leírásával. (Például: pálya generáláshoz implementált algoritmus.), valamint az esemény/eseménykezelő párosításokat és a tevékenység rövid leírását.
 A feladatleírás a minimális követelményeket tartalmazza. A játékok tetszőlegesen
bővíthetők.

Labirintus (Labyrinth)
Készítsünk programot, amellyel egy labirintusból való kijutást játszhatunk. A játékos a
labirintus bal alsó sarkában kezd, és a feladata, hogy minél előbb eljusson a jobb felső
sarokba úgy, hogy négy irányba (balra, jobbra, fel, vagy le) mozoghat, és elkerüli a
labirintus sárkányát.
Minden labirintusban van több kijutási útvonal. A sárkány egy véletlenszerű
kezdőpozícióból indulva folyamatosan bolyong a pályán úgy, hogy elindul valamilyen
irányba, és ha falnak ütközik, akkor elfordul egy véletlenszerűen kiválasztott másik
irányba. Ha a sárkány a játékossal szomszédos területre jut, akkor a játékos meghal.
Mivel azonban a labirintusban sötét van, a játékos mindig csak 3 sugarú körben látja a
labirintus felépítését, távolabb nem. Tartsuk számon, hogy a játékos mennyi labirintuson
keresztül jutott túl és amennyiben elveszti az életét, mentsük el az adatbázisba az
eredményét. Egy menüpontban legyen lehetőségünk a 10 legjobb eredménnyel rendelkező
játékost megtekinteni, az elért pontszámukkal, továbbá lehessen bármikor új játékot
indítani egy másik menüből. Ügyeljünk arra, hogy a játékos, vagy a sárkány ne falon
kezdjenek.