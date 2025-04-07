# Training alkalmazás

## Bevezetés

A Training alkalmazás résztvevőket kezel, akik jelentkezni tudnak különböző meghirdetett képzésekre.

Használati esetek:

* Résztvevőt regisztrálni lehet
* Képzést meg lehet hirdetni
* Résztvevők jelentkezhetnek képzésekre (csoportos jelentkezés, egyszerre több résztvevő is jelentkezhet)

Entitások:

* Résztvevő: név, feliratkozott-e hírlevélre
* Képzés: név, képzés típusa (élő, mentorált, e-learning), jelentkezett résztvevők

Szótár:

* Résztvevő: participan
* Képzés: training
* Regisztál: register
* Meghirdet: announce
* Jelentkezik: enroll

## Architektúra

Az alkalmazás csak Java SE-t használ, nincs benne keretrendszer.

Léteznek entitások. A repository a memóriában tárolja az entitásokat. Legfelső réteg a
service réteg.

Architektúrával kapcsolatos megfigyelések:

* Entitáson nincsenek setter metódusok
* Nem publikus konstruktorok, statikus metódusok példányosítanak. Ekkor ugyanis a használati eset neve szerint el lehet nevezni.
* Az entitások között nincs referencia. A Training csak a Participant id-jára hivatkozik. 
  Ez laza kapcsolatot tesz lehetővé, bármikor szétszedhetőek akár külön microservice-be.
* DTO-k recordok
* Üzleti logika: egy résztvevő csak egyszer jelentkezhet. Ezt a `Set` adattípus megoldja.
* Constructor injection, `Context` osztály végzi

Látható, hogy DDD-s elemeket tartalmaz, a `participant` és `training`
csomag egy-egy Aggregate-et tartalmaz, mivel egy entitás van benne, az az
Aggregate Root. 

## Feladatok

A képzőközpont az AI bevezetését tervezi. Azonban ez költséges, így limitálná a résztvevők által egy képzésen felhasználható
tokenek mennyiségét.

### 1. feladat

A résztvevők cége választhat, hogy résztvevőnként kifizet előre valamennyi tokent, vagy a résztvevők korlátlanul használhatnak fel tokeneket.
Ha előre kifizet, akkor meg kell adni, hogy mennyit.

A résztvevő az első esetben annyi tokent használhat fel, amennyi van neki. A második esetben bármennyi tokent felhasználhat.

Az első esetben le lehet kérdezni, hogy kezdetben mennyi tokene volt, mennyit használt fel, és mennyi maradt. A második esetben csak
a felhasznált tokeneket lehet lekérdezni.

_Megszorítás: nem lehet változtatni az eddigi kódot!_

### 1. feladat megoldása

Architecture decision records:

* Mivel nem lehet módosítani az eddigi kódot, a tokenek kezelése külön Aggregate-be és `token` csomagba kerül.
* API first tervezéssel kezdtünk. Azonban a service réteget fogalmaztuk meg először,
  DDD-t figyelembe véve lehet, hogy az entitással kellett volna kezdeni.
* Egyelőre nem TDD-ztünk, azonban az interfész kialakításában segített volna.
* Adhattunk volna egyedi azonosítót is a résztvevő tokenjeinek, azonban a 
  résztvevő azonosítója és a képzés azonosítója természetes azonosítóként működik
* A résztvevő azonosítója és a képzés azonosítója gyakran együtt jár, `Enrollment`
  recordba emeltük ki
* Valahogy jelezni kell, hogy bizonyos esetben meghatározott számú tokent kap,
  bizonyos esetben pedig végtelen számú tokennel rendelkezik. Létrehoztunk egy
  `TokenLimit` interface-t, és két implementációját, melyek record-ok:
  `LimitedTokens` vagy `UnlimitedTokens`. (Másik megoldás, hogy két metódust hozunk létre, különböző paraméterezéssel.) 
* Ugyanez a kérdés a visszatérési értéknél is. Ugyanez a megoldás, `TokenUsage` interfész,
  `LimitedTokenUsage`, `UnlimitedTokensUsage` implementációkkal.
* `Token` Aggregate-ben implementáció switch használatával
* Kerüljük a szintetikus nevek használatát. Törekedjünk a Ubiquitous Language használatára (üzlet által is használt 
  fogalmak), valamint a keretrendszer által bevezetett fogalmak (controller, service, repository) használatára.
* Kivételkezelés best practices:
  * Használjunk unchecked kivételeket
  * Próbáljuk meg a létező kivételeket használni
  * Csak akkor használjunk egyedi kivételeket, ha másképp is akarjuk kezelni
  * Lehető legtöbb információt tegyünk a kivételbe
* Használtuk a data-oriented programming megközelítést, kombináltuk a pattern matching for switch,
  sealed classes, unnamed variables, record patterns nyelvi elemeket.
* `hasAvailable` kiszámítása a Value Objectekbe került

Új szótár:

* Token
* Jelentkezés: enrollment
* Jelentkezés előre meghatározott tokenekkel: limited token
* Jelentkezés korlátlan tokenekkel: unlimited token
* Tokenek kezdeti meghatározása: assign token
* Tokenhasználat: token usage

Hozzáadva: `TokenRepository` és triviális `TokenService` metódus implementációk.