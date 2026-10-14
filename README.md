# TP1-DAR2026

Répertoire de travail : `chahinsellami_LSI3_DevAppReparties`

## Activité 1-1 : Première connexion Client/Serveur

Cette activité met en œuvre une communication TCP en mode connecté entre
deux applications Java :

- `serverPackage.Server` ouvre le port `5000`, attend une connexion et reçoit
  un message ;
- `clientPackage.Client` se connecte à `localhost:5000`, envoie un message et
  affiche la réponse du serveur.

## Pré-requis

- JDK 8 ou version ultérieure ;
- Git.

## Étape 1 : Compilation du projet

Depuis la racine du projet :

```powershell
New-Item -ItemType Directory -Force out
javac -encoding UTF-8 -d out src\serverPackage\Server.java src\clientPackage\Client.java
```

Cette commande compile les classes `Server` et `Client` dans le répertoire
`out`.

## Étape 2 : Lancement du serveur

Ouvrir un premier terminal dans la racine du projet et lancer :

```text
java -cp out serverPackage.Server
```

Le serveur ouvre le port TCP `5000`, puis attend la connexion d'un client. La
capture d'écran du serveur présente le message d'attente :

```text
Je suis un serveur en attente de la connexion d'un client
```

## Étape 3 : Lancement du client

Ouvrir un second terminal dans la racine du projet et lancer :

```text
java -cp out clientPackage.Client
```

Le client se connecte au serveur, lui envoie le message `test hello`, puis
affiche la réponse reçue :

```text
resposne: Connexion estableshed.
```

Après la connexion, le premier terminal affiche :

```text
un client est connect
Message : test hello
```

## Captures d'écran

Les captures suivantes documentent les étapes d'exécution demandées dans
l'activité.

### Résultat du client

![Console du client](screenshots/client-console.png)

Cette capture montre l'exécution de la classe `clientPackage.Client`. La
réponse confirme que la connexion TCP avec le serveur a été établie.

### Résultat du serveur

![Console du serveur](screenshots/server-console.png)

Cette capture montre l'exécution de la classe `serverPackage.Server`. Les
messages confirment qu'un client s'est connecté et que le serveur a reçu
`test hello`.

Pour une connexion entre deux machines, remplacer `localhost` dans
`Client.java` par l'adresse IP de la machine qui exécute le serveur. Le port
`5000` doit alors être accessible sur le réseau.

## Dépôt

Le dépôt GitHub doit être nommé `TP1-DAR2026`. Après sa création, ajouter son
lien ici et le déposer dans l'espace prévu sur la plateforme ENT.
