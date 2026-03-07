# Mini Planner (ToDo App)

Mini Planner on lihtne Android ToDo rakendus, mis võimaldab kasutajal lisada, 
muuta ja kustutada ülesandeid.  

Rakendus toetab ka ülesande märkimist tehtuks ning kuupäeva lisamist.  
Kõik ülesanded salvestatakse seadme lokaalsesse salvestusse (JSON), 
seega jäävad need alles ka pärast rakenduse sulgemist.

## Features

Rakendus võimaldab:

- lisada uusi ülesandeid
- muuta ülesande teksti
- kustutada ülesandeid
- märkida ülesanne tehtuks / tegemata
- lisada ülesandele valikuline kuupäev
- kontrollida kuupäeva formaati (DD.MM.YYYY)
- salvestada ülesanded lokaalsesse salvestusse (JSON)
- laadida salvestatud ülesanded rakenduse käivitamisel

## Tech stack

Rakendus on loodud kasutades:

- **Kotlin**
- **Jetpack Compose**
- **Navigation Compose**
- **Material 3**
- **Android Studio**
- **SharedPreferences** (JSON storage)

## Kuidas rakendust käivitada

1. Ava projekt **Android Studios**
2. Sünkroniseeri Gradle failid (Sync Project with Gradle Files)
3. Käivita rakendus emulaatoris või Android seadmes
4. Rakendus avaneb ülesannete nimekirja ekraaniga

## Team members

**Jekaterina Shashkina**
- kasutajaliidese (UI) disain
- ekraanide loomine Jetpack Compose abil
- komponentide ja stiilide loomine
- CRUD loogika (add / edit / delete / toggle)

**Nadia Artamonova**
- ülesannete oleku haldus (state management)
- CRUD loogika (add / edit / delete / toggle)
- ülesannete salvestamine ja laadimine JSON salvestusest

## Ekraanipildid

### Task list screen
Sellel ekraanil kuvatakse kõik ülesanded.  

Kasutaja saab märkida ülesande tehtuks, seda muuta või kustutada.

![Task list](screenshots/TaskScreen.png)
### Add Task Form
Sellel vormil saab kasutaja lisada uue ülesande ning soovi korral määrata kuupäeva.

![Task list](screenshots/AddTaskForm.png)
### Add Task Screen
Sellel ekraanil kasutaja juba sisestas uue ülesande pealkirja ning kuupäeva.  

Ülesanne salvestatakse pärast nupu **Save** vajutamist.

![Task list](screenshots/AddTaskScreen.png)
### Edit Task
Kasutaja saab olemasolevat ülesannet muuta otse nimekirjas.  

Muudatuse kinnitamiseks kasutatakse linnukese nuppu ning tühistamiseks ristiga nuppu.

![Task list](screenshots/EditTask.png)
### Completed Task
Kui kasutaja märgib ülesande tehtuks, muutub selle olek.  

Ülesanne kuvatakse läbikriipsutatud tekstiga ning märgitakse punase värviga.

![Task list](screenshots/CompletedTask.png)

## GitHub workflow

Arendus toimus ainult feature-harudes, `main` hoiti stabiilsena.
Kõik muudatused liideti `main` harusse ainult Pull Request'ide kaudu.

### Pull Requestid

| PR | Branch | Peamine sisu | Autor
|---|---|---|---|
| #1 | `feature-ui-list-screen` | Task list ekraan, task item UI, edit/delete/toogle done tegevused | Jekatenina |
| #2 | `feature-ui-add-task-screen` | Add Task ekraan, kuupäeva valideerimine, taaskasutatavad nupud | Jekatenina |
| #3 | `feature-add-task-state` | Task state ja CRUD loogika | Nadezda |
| #4 | `feature-storage-sharedprefs` | SharedPreferences JSON salvestus/laadimine, ViewModel refactor | Nadezda |

### Code review

Iga PR sai review-kommentaare enne merge'i.
Vajadusel tehti parandused samas harus ja alles seejärel merge.

## Testing

Automaatseid teste ei lisatud.
Projekt testiti käsitsi Android emulaatoris.

### Manual test cases

- [x] Rakendus käivitub Android Studios/emulaatoris.
- [x] Uue ülesande lisamine töötab.
- [x] Ülesannete nimekiri kuvatakse korrektselt.
- [x] `Done` staatuse muutmine (checkbox) töötab.
- [x] Ülesande kustutamine töötab.
- [x] Ülesande muutmine (inline edit) töötab.
- [x] Horisontaalrežiimis (landscape) paigutus jääb kasutatavaks.
- [x] Pikkadel ekraanidel/vormidel töötab vertikaalne kerimine (scroll).
- [x] Andmed säilivad pärast äpi sulgemist/uuesti avamist (SharedPreferences).

## Video Demo

[![Watch the demo](screenshots/list_screen.png)](https://youtube.com/shorts/IgDSrtqNEXE?feature=share)

