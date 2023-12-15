package MindHub.ecommerce;

import MindHub.ecommerce.models.*;
import MindHub.ecommerce.repositories.ClientRepository;
import MindHub.ecommerce.repositories.CreamRepository;
import MindHub.ecommerce.repositories.FlavoringRepository;
import MindHub.ecommerce.repositories.FragranceRepository;
import MindHub.ecommerce.services.FragranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static MindHub.ecommerce.models.Presentation.*;

@SpringBootApplication
public class ECommerceApplication {

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, FragranceRepository fragranceRepository,
                                      FlavoringRepository flavoringRepository, CreamRepository creamRepository)
    {
        return args -> {

//-------------------------------------------CLIENTS-------------------------------------------

            Client client1 = new Client("Ariana", "Alochis", "ari@velvetadm.com", passwordEncoder.encode("1234"));
            clientRepository.save(client1);

            Client client2 = new Client("Isaac", "Alfonso", "ifa@velvetadm.com", passwordEncoder.encode("isaac"));
            clientRepository.save(client2);



//-------------------------------------------FRAGRANCES-------------------------------------------

            Fragrance fragrance1 = new Fragrance("Reine Eau de parfum",
                    "A pure and bright aroma.\n" + " The fragrance of a conquering, empowered and strong woman, who expresses a new era for those who dream big.\n" + " Unstoppable, you can make your own path and achieve what you want.\n" + " Head: pear and bergamot.\n" + " Heart: Turkish rose, Mai rose and Indian jasmine.\n" + " Base: white musk and vanilla",
                    Gender.WOMEN, OlfactoryFamily.CITRUS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701103421/reine1-28bf2c4ffbfad8b6fb16859777988887-1024-1024_ru9afi.jpg",
                    39.99, Presentation.CLASICPACKAGE, 50, 10, true);
            fragranceRepository.save(fragrance1);

            Fragrance fragrance2 = new Fragrance("Manhattan Rose Eau de parfum",
                    "Its glamorous spirit makes you shine day and night. A fresh, dynamic and essentially feminine fragrance, which expresses the rhythm of the big city.\n" + "- Top notes: rosé champagne and pink pepper\n" + "- Heart notes: peach blossom and bouquet of roses\n" + "- Base notes: queenwood and musk",
                    Gender.WOMEN, OlfactoryFamily.FLOWERY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701103419/manhattan-rose1-7194d2de97952d1a9716859814625038-480-0_dapnhn.jpg",
                    39.99, Presentation.CLASICPACKAGE, 50, 18, true);
            fragranceRepository.save(fragrance2);

            Fragrance fragrance3 = new Fragrance("Marsella Eau de parfum",
                    "A breath of fresh air and freedom. Intoxicating, unbridled and enigmatic, an addictive aroma that invites you to an irreverent adventure in the middle of the night.\n" +
                            "Top notes: honey, bitter orange and citrus\n" +
                            "Heart notes: cherry, tuberose and orange blossom\n" +
                            "Base notes: tonka bean, vanilla, patchouli and sandalwood",
                    Gender.WOMEN, OlfactoryFamily.FLOWERY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701103415/5975f1c6777d862572f61e59ce85c7b7932ecb30e57f50a795cb231f472b154685272_gcayci.png",
                    69.99, Presentation.CLASICPACKAGE, 100, 23, true);
            fragranceRepository.save(fragrance3);

            Fragrance fragrance4 = new Fragrance("Las Vegas Eau de parfum",
                    "Charisma and insolent, provocative energy. The fragrance of those who get what they want.\n" +
                            "Head: grapefruit, mint, red mandarin.\n" +
                            "Heart: rose, cinnamon, spicy notes.\n" +
                            "Base: leather, woody notes, Indian patchouli.",
                    Gender.MAN, OlfactoryFamily.WOODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691468/las-vegas1-06109c28078b94699416868369344433-480-0_m97hf2.jpg",
                    39.99, Presentation.CLASICPACKAGE, 50, 15, true);
            fragranceRepository.save(fragrance4);

            Fragrance fragrance5 = new Fragrance("Femme Eau de parfum",
                    "The celebration of a new era of femininity, joyful, sensual and absolutely irresistible. A fragrance that transmits the spirit and strength of a self-confident woman.\n" +
                            "Top notes: bergamot, mandarin and mango" +
                            "Heart notes: jasmine and incense\n"
                            + "Base notes: patchouli, sandalwood and vanilla",
                    Gender.WOMEN, OlfactoryFamily.FLOWERY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701727686/femme1-a3485d81867a41b86016859815160270-480-0_ih88rt.jpg",
                    29.99, Presentation.CLASICPACKAGE, 50, 15, true);
            fragranceRepository.save(fragrance5);

            Fragrance fragrance6 = new Fragrance("París Eau de parfum",
                    "Fresh, delicious and with a delicate personality, like the women who choose it\n" +
                            "A joyful fragrance that blooms to spark smiles and radiate happiness\n" +
                            "Top notes: black currant, pear\n" + "Heart notes: iris, jasmine, orange blossom\n" +
                            "Base notes: patchouli, tonka bean, vanilla",
                    Gender.WOMEN, OlfactoryFamily.FLOWERY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701728506/paris1-ac6e10b4221cf4227c16868370552790-1024-1024_v7nh4o.jpg",
                    29.99, Presentation.CLASICPACKAGE, 50, 17, true);
            fragranceRepository.save(fragrance6);

            Fragrance fragrance7 = new Fragrance("Ámsterdam homme Eau de parfum",
                    "To feel everything on the surface, you only need the explosive sensuality of this fragrance.\n" +
                            "I raised the intensity with a fresh, irreverent and fiery aroma, which is pure instinct.\n" +
                            "Head: ginger, thyme, grapefruit, bergamot and green accords\n" + "Heart: vanilla, liqueur, cinnamon, leather and apple\n" +
                            "Base: myrrh, sugar, cedar, woody notes, cashmere and patchouli",
                    Gender.MAN, OlfactoryFamily.WOODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701729657/amsterdam-homme1-30512c1d810602bee116859817158894-480-0_caq3ob.jpg",
                    34.99, Presentation.CLASICPACKAGE, 50, 15, true);
            fragranceRepository.save(fragrance7);

            Fragrance fragrance8 = new Fragrance("Esparta Victory Eau de parfum",
                    "A fresh aroma endowed with extreme, powerful and sensual vigor.\n" +
                            "Nothing can stop you from pursuing feats and defeat is not an option.\n " +
                            "A captivating fragrance that expresses the magnetism of always winning.\n" +
                            "Head: black pepper, incense, pink pepper\n" + "Heart: lavandin, lemon.\n" +
                            "Base: amber, patchouli, vanilla",
                    Gender.MAN, OlfactoryFamily.WOODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701729920/16211d0093250e8ddc251f8dd3bd455242d12d6f977efc72f9cb8b2330463697198545_nxuut8.png",
                    15.99, Presentation.CLASICPACKAGE, 50, 20, true);
            fragranceRepository.save(fragrance8);

            Fragrance fragrance9 = new Fragrance("Mónaco Blue Eau de parfum",
                    "In every drop, the freedom of the open sea. " +
                            "I felt a rush of fresh air and the fullness of an infinite blue sky that reenergizes you, to live life to the fullest.\n" +
                            "Top: cantaloupe melon, cucumber, watery melon and bergamot oil.\n" +
                            "Body: aquatic note, clary sage oil, geranium oil, verbena oil and basil.\n" +
                            "Base: washed suede, heart of patchouli and pure musk.",
                    Gender.MAN, OlfactoryFamily.CITRUS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701730260/monaco-blue1-7f1ec27eecb549d0e316859789348964-640-0_qa333l.jpg",
                    25.99, Presentation.CLASICPACKAGE, 100, 18, true);
            fragranceRepository.save(fragrance9);

            Fragrance fragrance10 = new Fragrance("New York Homme Eau de parfum",
                    "The city never stops and neither do you. " +
                            "Fresh, sensual and elegant, a vibrant and magnetic fragrance that is an affirmation of freedom.\n" +
                    "Head: flower stems and bergamot.\n" +
                            "Heart: gardenia and jasmine.\n" +
                            "Base: sandalwood and musk.",
                    Gender.MAN, OlfactoryFamily.FOUGÉRE,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701730954/86c506616402f7b7a50b8071d3aaaca9a8edcd3b4e997ab3008e9a06f7a0b014198545_n8etjk.png",
                    24.99, Presentation.CLASICPACKAGE, 50, 12, true);
            fragranceRepository.save(fragrance10);



//-------------------------------------------CREAMS-------------------------------------------

            Cream cream1 = new Cream("Exotic cream",
                    "Returns the necessary hydration and softness to your skin. " +
                            "Formulated with jojoba, it helps renewal and leaves it radiant and scented with the unmistakable citrus aroma of Exotic.",
                    9.99, 300, 15, Type.BODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701347624/exoticcremacorporal1-0f5f697422c8125da416859756562113-1024-1024_yepahd.png",
                    true);
            creamRepository.save(cream1);

            Cream cream2 = new Cream("Original hand cream",
                    "Promotes immediate and prolonged hydration in your hands.\n" +
                            "With a creamy texture and quick absorption, apply it to leave your skin soft and delicately scented.",
                    8.99, 60, 2, Type.HAND,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691729/desodorante-antitranspirante-300x300_x9vfaw.png",
                    true);
            creamRepository.save(cream2);

            Cream cream3 = new Cream("Blush hand cream",
                    "Its light texture provides the necessary care and brings the distinctive aroma of freshness and confidence to your hands to accompany you all day.",
                    8.99, 60, 18, Type.HAND,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701731648/crema-de-manos-blush1-f6c1771602360fffdf16859758858051-480-0_uy93ul.png",
                    true);
            creamRepository.save(cream3);

            Cream cream4 = new Cream("Night facial treatment cream",
                    "Provides all the antioxidant power of vitamin C.\n" +
                            "Brightens and helps reduce spots and wrinkles.\n" +
                            "Benefits all types of oily, dry or combination skin.",
                    11.99, 50, 14, Type.FACIAL,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701731742/Crema-1_z6kr5o.png",
                    true);
            creamRepository.save(cream4);

            Cream cream5 = new Cream("Deep exfoliation gel cream",
                    "Formula with microspheres that deeply cleanses the lower layers of the skin and removes dead cells. " +
                            "Leave your skin smooth and free of impurities.",
                    9.99, 95, 17, Type.FACIAL,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701731880/Gel_exfoliacion_nuevo-1_bnfc94.png",
                    true);
            creamRepository.save(cream5);

            Cream cream6 = new Cream("Moisturizing cream for men",
                    "Moisturizes instantly and with a long-lasting effect.\n" +
                            "It has dermospheres with vitamin E that complement the cell renewal process and visibly improve the tone and general appearance of the skin.\n" +
                            "Soft and light texture that does not leave shine.",
                    10.99, 95, 24, Type.FACIAL,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701731963/etapehomme1-df61c6ebb14604775516859762521745-640-0_sl32il.png",
                    true);
            creamRepository.save(cream6);

            Cream cream7 = new Cream("Moisturizing body cream",
                    "Formulated with natural ingredients that care for the skin 24 hours a day and stimulate its regeneration.\n" +
                            "Your ally cream to show off your soft and smooth skin.",
                    9.99, 300, 17, Type.BODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701732349/crema-corporal-hidratante1-c4784b8144745a373016859757540985-480-0_edyips.png",
                    true);
            creamRepository.save(cream7);

            Cream cream8 = new Cream("Glow moisturizing body cream",
                    "Moisturizes and soothes dry skin instantly thanks to its formula with hydromanyl, a plant-based moisturizing active ingredient with an immediate and cumulative moisturizing effect that improves the appearance, leaving it soft and luminous.",
                    11.99, 300, 6, Type.BODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691743/SempliceGlow-2-2_tfcwra.png",
                    true);
            creamRepository.save(cream8);

            Cream cream9 = new Cream("Blush moisturizing body cream",
                    "It takes care of you with a super light texture and leaves an exquisite aroma that accompanies you all day. Formulated to give you skin-deep softness.",
                    11.99, 300, 12, Type.BODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701347637/crema-blush_wuex3p.png",
                    true);
            creamRepository.save(cream9);

            Cream cream10 = new Cream("Moisturizing cream for tired feet",
                    "It helps improve circulation and relieve heaviness, provides freshness and produces a feeling of rest. It has a relaxing effect on tension caused in the muscles, revitalizes, tones and reduces inflammation.",
                    9.99, 95, 14, Type.BODY,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701732811/Crema-pies_ov3i88.jpg",
                    true);
            creamRepository.save(cream10);



//-------------------------------------------FLAVORING-------------------------------------------

            Flavoring flavoring1 = new Flavoring("Pink and violet air freshener",
                    "Scent of roses and violets. Ideal for perfuming every corner of your home.", 500, 15.99, 9,
                    AMBIENT,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701276850/aromatizante-rosa-y-violeta_zduwqa.png",
                    true);
            flavoringRepository.save(flavoring1);

            Flavoring flavoring2 = new Flavoring("Strawberry and plum air freshener",
                    "A fruity essence that seduces your senses and transmits the freshness of summer.", 500, 14.99, 4,
                    AMBIENT,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691772/aromatizante-frutilla-y-ciruela_ayyipu.png",
                    true);
            flavoringRepository.save(flavoring2);

            Flavoring flavoring3 = new Flavoring("Fabric air freshener caress of freshness",
                    "Energy and freshness between your sheets and pillows to start the day with the best aromas.", 500,
                    16.99, 7, FABRICS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691760/aromatizante-cama-caricia-de-frescura_t81iuq.png",
                    true);
            flavoringRepository.save(flavoring3);

            Flavoring flavoring4 = new Flavoring("Linen air freshener",
                    "An unmistakable fragrance of clean clothes that permeates all your environments.", 500, 14.99, 6,
                    AMBIENT,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701691764/aromatizante-lino_bvxhq8.png", true);
            flavoringRepository.save(flavoring4);

            Flavoring flavoring5 = new Flavoring("orchid and jasmine air freshener diffuser",
                    "A floral fragrance that feels like holding a bouquet of flowers in your hands.", 150, 18.99, 2,
                    DIFFUSERS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701733071/difusor-orquidea_o0yxvl.png", true);
            flavoringRepository.save(flavoring5);

            Flavoring flavoring6 = new Flavoring("palo santo air freshener diffuser",
                    "The rattan rods absorb the sweet essence and spread it through the air currents of the environment to help you harmonize it. Enjoy your favorite aroma for much longer.", 150, 17.99, 8,
                    DIFFUSERS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701733068/PaloSanto_qwieum.png", true);
            flavoringRepository.save(flavoring6);

            Flavoring flavoring7 = new Flavoring("peach and lemon air freshener diffuser",
                    "A citrus and powerful fragrance that transmits joy and well-being for much longer.", 150, 14.99, 16,
                    DIFFUSERS,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701733065/difusor-durazno_ycburu.png", true);
            flavoringRepository.save(flavoring7);

            Flavoring flavoring8 = new Flavoring("Linen air freshener diffuser",
                    "A floral fragrance that permeates all your environments with an unmistakable aroma of clean clothes.", 150, 14.99, 6,
                    AMBIENT,
                    "https://res.cloudinary.com/dndulhlws/image/upload/v1701733062/difusor-lino_wejkug.png", true);
            flavoringRepository.save(flavoring8);

        };
    }
}