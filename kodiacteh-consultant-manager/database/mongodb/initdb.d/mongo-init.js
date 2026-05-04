db = db.getSiblingDB("consultant_db");

// Création explicite
db.createCollection("utilisateurs");

//creation user pour bdd
db.createUser({
  user: "kodiatech",
  pwd: "kodiapwd",
  roles: [
    { role: "readWrite", db: "consultant_db" }
  ]
});

// Index unique
db.utilisateurs.createIndex({ email: 1 }, { unique: true });

// Insertion
db.utilisateurs.insertOne({
    nom: "diaby",
    prenom: "traore",
    password: "$2a$10$1YuGJeaKn5PDxIP7lULQaujY0v4cGsrXuPuEpoozhMsdO1ZOfmKWm",
    email: "t@gmail.com",
    telephone: "+33-06-61-08-95-10",
    fonctionTitle: "Tech-lead",
    adresse: {
        adresse: "Thomas Edison",
        complementAdresse: "bat A, appt 68",
        codePostal: "31400",
        ville: "Toulouse",
        pays: "France"
    },
    role: "ADMIN"
});

print("Base consultant_db initialisée avec succès");