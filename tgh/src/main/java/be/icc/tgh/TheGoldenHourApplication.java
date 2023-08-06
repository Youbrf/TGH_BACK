package be.icc.tgh;

import be.icc.tgh.faker.Generator;
import be.icc.tgh.model.*;
import be.icc.tgh.service.CategorieServiceS;
import be.icc.tgh.service.ReservationS;
import be.icc.tgh.service.ServiceS;
import be.icc.tgh.service.UserS;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TheGoldenHourApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheGoldenHourApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServiceS s, CategorieServiceS c, ReservationS r, UserS u) {
        return args -> {
            u.creerUser(new User("admin@gmail.com", "admin", Role.ADMIN));
            u.creerUser(new User("user@gmail.com", "user", Role.USER));
            c.creerCategorieService(new CategorieService(null, "Beauté Des Mains", "Offrez-vous une expérience de détente ultime pour vos mains avec notre service de beauté des mains. Nos esthéticiennes expérimentées prendront soin de vos ongles, cuticules et vous offriront un massage apaisant pour laisser vos mains douces et revitalisées."));
            c.creerCategorieService(new CategorieService(null, "Beauté Des Pieds", "Détendez-vous et prenez soin de vos pieds fatigués avec notre service de beauté des pieds. Nos spécialistes chouchouteront vos pieds en les exfoliant, en taillant vos ongles, et en vous offrant un massage relaxant pour que vous vous sentiez comme si vous marchiez sur un nuage."));
            c.creerCategorieService(new CategorieService(null, "Faux Ongles", "Obtenez des ongles magnifiquement conçus avec notre service de faux ongles. Nos professionnels de l'onglerie vous proposeront un large éventail de styles, de couleurs et de motifs pour que vos ongles soient élégants et attrayants."));
            c.creerCategorieService(new CategorieService(null, "Beauté Du Regard", "Mettez en valeur vos yeux avec notre service de beauté du regard. Nos experts en beauté vous offriront des traitements spéciaux pour vos sourcils et vos cils, vous donnant un regard captivant et expressif."));
            c.creerCategorieService(new CategorieService(null, "Soin Du Visage", "Prenez soin de votre peau avec notre service de soin du visage. Nos esthéticiennes qualifiées utiliseront des produits de qualité pour nettoyer, exfolier et hydrater votre peau, la laissant éclatante et rafraîchie."));
            c.creerCategorieService(new CategorieService(null, "Beauté De Regard", "Offrez-vous une expérience complète pour sublimer votre regard avec notre service de beauté de regard. Des soins pour vos sourcils et vos cils seront combinés avec des traitements spéciaux pour vos yeux, vous faisant rayonner de beauté."));
            s.creerService(new Service("Pose de vernis - mains", "Le soin débute par un limage doux sur le bord libre de l'ongle afin de corriger les petites imperfections et les irrégularités. Votre experte va ensuite opérer un polissage soigneux à l'aide d'un polissoir pour rendre les ongles lisses et leur redonner tout leur éclat.", 15, 15, c.getCategorieServiceByID(1L)));
            s.creerService(new Service("Pose de vernis semi-permanent - mains", "La pose de vernis semi-permanent débute par un limage léger pour préparer les ongles. Ensuite, une base est appliquée suivie de deux couches de vernis semi-permanent. Chaque couche est durcie sous une lampe UV pour une tenue longue durée.", 60, 40, c.getCategorieServiceByID(1L)));
            s.creerService(new Service("Manucure spa + pose vernis classique", "La manucure spa est un soin complet pour vos mains. Elle comprend le limage, le polissage, le soin des cuticules, un gommage doux pour exfolier la peau, un massage relaxant et enfin, l'application du vernis classique de votre choix.", 40, 30, c.getCategorieServiceByID(1L)));
            s.creerService(new Service("Soin à la paraffine mains", "Le soin à la paraffine est un traitement hydratant pour vos mains. Vos mains sont plongées dans de la paraffine chaude, puis enveloppées dans des serviettes pour permettre à la paraffine de pénétrer la peau. Ce soin laissera vos mains douces et nourries.", 30, 30, c.getCategorieServiceByID(1L)));
            s.creerService(new Service("Pack semi-permanent mains et pieds", "Ce pack comprend une pose de vernis semi-permanent sur vos mains et vos pieds. Le vernis semi-permanent est durable et résistant aux éclats, vous offrant une manucure et une pédicure parfaites pendant plusieurs semaines.", 120, 75, c.getCategorieServiceByID(1L)));
            s.creerService(new Service("Pack soins paraffine + manucure vernis classique", "Ce pack combine le soin à la paraffine pour vos mains et une manucure avec application de vernis classique. Vos mains seront nourries en profondeur grâce à la paraffine, et vos ongles seront sublimés avec le vernis de votre choix.", 70, 55, c.getCategorieServiceByID(1L)));
            s.creerService(new Service("Dépose de vernis semi-permanent mains", "La dépose de vernis semi-permanent consiste à retirer délicatement le vernis existant sans abîmer vos ongles naturels. Une fois le vernis retiré, vos ongles sont limés pour préparer la pose d'un nouveau vernis semi-permanent si vous le souhaitez.", 30, 20, c.getCategorieServiceByID(1L)));
            s.creerService(new Service("Pose de vernis - pieds", "Le soin débute par un bain relaxant pour vos pieds suivi d'un limage doux pour façonner vos ongles. Vos cuticules sont soigneusement repoussées, puis vos ongles sont polis pour leur donner de l'éclat. Enfin, le vernis est appliqué pour une touche finale parfaite.", 15, 15, c.getCategorieServiceByID(2L)));
            s.creerService(new Service("Pose de vernis semi-permanent - pieds", "La pose de vernis semi-permanent pour vos pieds permet d'avoir des ongles impeccables pendant des semaines. Une base est appliquée pour protéger vos ongles, suivie de deux couches de vernis semi-permanent. Les ongles sont ensuite durcis sous une lampe UV.", 60, 40, c.getCategorieServiceByID(2L)));
            s.creerService(new Service("Pédicure spa + pose vernis classique", "La pédicure spa est un soin complet pour vos pieds. Elle comprend un bain relaxant, le limage des ongles, le soin des cuticules, un gommage doux pour éliminer les peaux mortes, un massage apaisant et enfin, l'application du vernis classique de votre choix.", 60, 50, c.getCategorieServiceByID(2L)));
            s.creerService(new Service("Pédicure spa + vernis semi-permanent", "Ce soin combine une pédicure spa relaxante avec une pose de vernis semi-permanent. Vos pieds seront chouchoutés et vos ongles sublimés avec un vernis longue durée qui résiste aux éclats.", 75, 80, c.getCategorieServiceByID(2L)));
            s.creerService(new Service("Soin à la paraffine - pieds", "Le soin à la paraffine pour vos pieds permet de les hydrater en profondeur et de soulager les pieds fatigués. Vos pieds seront enveloppés dans de la paraffine chaude, laissant votre peau douce et apaisée.", 35, 30, c.getCategorieServiceByID(2L)));
            s.creerService(new Service("Dépose de vernis semi-permanent - pieds", "La dépose de vernis semi-permanent pour vos pieds est réalisée en douceur pour préserver vos ongles naturels. Une fois le vernis retiré, vos ongles sont soignés et préparés pour accueillir une nouvelle pose de vernis semi-permanent.", 25, 20, c.getCategorieServiceByID(2L)));
            s.creerService(new Service("Pose d'ongles en résine / Acrylique", "La pose d'ongles en résine ou acrylique permet d'obtenir des ongles longs et résistants. La résine/acrylique est modelée sur vos ongles naturels ou sur des capsules pour créer la forme et la longueur souhaitées. Enfin, vos ongles sont limés et polis pour un résultat impeccable.", 110, 65, c.getCategorieServiceByID(3L)));
            s.creerService(new Service("Retouche d'ongles en résine / Acrylique", "La retouche d'ongles en résine/acrylique permet de rafraîchir votre pose existante. Les ongles sont limés pour ajuster la longueur, puis une nouvelle couche de résine/acrylique est appliquée pour renforcer vos ongles et leur donner un aspect neuf.", 90, 55, c.getCategorieServiceByID(3L)));
            s.creerService(new Service("Dépose d'ongles en gel/acrylique", "La dépose d'ongles en gel/acrylique est réalisée avec précaution pour préserver la santé de vos ongles naturels. Les couches de gel/acrylique sont enlevées délicatement sans endommager vos ongles. Vous pouvez ensuite opter pour une nouvelle pose ou garder vos ongles naturels.", 35, 25, c.getCategorieServiceByID(3L)));
            s.creerService(new Service("Teinture des sourcils", "La teinture des sourcils consiste à appliquer une teinture semi-permanente sur vos sourcils pour leur donner une couleur plus foncée et plus définie. Cela permet de mettre en valeur votre regard et de corriger d'éventuelles zones clairsemées.", 15, 20, c.getCategorieServiceByID(4L)));
            s.creerService(new Service("Pack teinture cils + sourcils", "Ce pack comprend la teinture des cils et des sourcils. La teinture des cils permet d'assombrir vos cils pour un effet mascara naturel sans maquillage. Associée à la teinture des sourcils, ce pack mettra en valeur vos yeux et votre regard.", 35, 35, c.getCategorieServiceByID(4L)));
            s.creerService(new Service("Soin du visage complet", "Le soin du visage complet est un traitement relaxant et revitalisant pour votre peau. Il comprend un nettoyage en profondeur, un gommage pour éliminer les cellules mortes, une extraction des comédons, un massage du visage, et enfin, l'application d'un masque adapté à votre type de peau.", 60, 70, c.getCategorieServiceByID(5L)));
            s.creerService(new Service("Teinture des cils", "La teinture des cils permet de foncer la couleur de vos cils pour un effet mascara naturel. Cela mettra en valeur vos yeux et donnera l'apparence de cils plus longs et plus épais sans avoir besoin de mascara.", 20, 25, c.getCategorieServiceByID(6L)));
            s.creerService(new Service("Dépose d'extensions de cils", "La dépose d'extensions de cils consiste à retirer délicatement les extensions de cils existantes. Ce processus est réalisé avec soin pour éviter d'endommager vos cils naturels. Vous pouvez ensuite choisir de faire poser de nouvelles extensions ou garder vos cils au naturel.", 30, 25, c.getCategorieServiceByID(6L)));

            // Création de l'instance du générateur avec les repositories
            Generator ReservationGenerator = new Generator(u, s, r);

            for (int i = 0; i < 100; i++) {
                User user = ReservationGenerator.generateFakeUser();
            }

            // Création et enregistrement des employeurs
            for (int i = 0; i < 5; i++) {
                User employer = ReservationGenerator.generateFakeEmployer();
            }

            // Génération des rendez-vous factices et enregistrement
            for (int i = 0; i < 1000; i++) {
                Reservation Reservation = ReservationGenerator.generateFakeReservation();
            }

        };
    }
}