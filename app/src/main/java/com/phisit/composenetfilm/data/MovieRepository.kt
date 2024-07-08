package com.phisit.composenetfilm.data

import com.phisit.composenetfilm.domain.MovieModel

interface MovieRepository {
    suspend fun getMovies(): List<MovieModel>
    suspend fun searchMovie(value: String): List<MovieModel>
}

class MovieRepositoryImp : MovieRepository {
    override suspend fun getMovies(): List<MovieModel> {
        return listOf(
            MovieModel(
                "1",
                "Squid Game",
                "Hundreds of cash-strapped players accept a strange invitation to compete in children's games. Inside, a tempting prize awaits with deadly high stakes: a survival game that has a whopping 45.6 billion-won prize at stake.",
                "https://www.movieposters.com/cdn/shop/products/54440_480x.progressive.jpg?v=1646167720",
                voting = 199,
                initialFavorite = true,
                rating = "8/10",
                comment = "200k",
                releaseDate = "12/05/2024"
            ),
            MovieModel(
                "2",
                "Blink Twice",
                "When tech billionaire Slater King meets cocktail waitress Frida at his fundraising gala, he invites her to join him and his friends on a dream vacation on his private island. As strange things start to happen, Frida questions her",
                "https://www.movieposters.com/cdn/shop/files/blink_twice_480x.progressive.jpg?v=1718830090",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 100,
                releaseDate = "13/01/2024"
            ),
            MovieModel(
                "3",
                "Mandalorian",
                "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.",
                "https://www.movieposters.com/cdn/shop/products/mandalorian_480x.progressive.jpg?v=1614199582",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 99,
                releaseDate = "13/04/2023"
            ),
            MovieModel(
                "4",
                "Sharknado",
                "When a freak hurricane swamps Los Angeles, nature's deadliest killer rules sea, land, and air as thousands of sharks terrorize the waterlogged populace.",
                "https://www.movieposters.com/cdn/shop/products/sharknado_480x.progressive.jpg?v=1624479837",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 50,
                releaseDate = "05/01/2023"
            ),
            MovieModel(
                "5",
                "Rose",
                "",
                "https://www.movieposters.com/cdn/shop/products/rose.mp_480x.progressive.jpg?v=1654181145",
                initialFavorite = false, rating = "8/10", comment = "200k", voting = 50,
                releaseDate = "05/01/2021"
            ),
            MovieModel(
                "6",
                "Nashville",
                "Over the course of a few hectic days, numerous interrelated people prepare for a political convention.",
                "https://www.movieposters.com/cdn/shop/products/d814d7f452ec9a1fe6d98344e97d962b_014db7c0-2410-4200-b50b-84583df9fbb0_480x.progressive.jpg?v=1573594939",
                initialFavorite = false, rating = "8/10", comment = "200k", voting = 400,
                releaseDate = "15/01/2022"
            ),
            MovieModel(
                "7",
                "Terrorists",
                "Multi-millionaire Tom Mullen's son is kidnapped, but after initially agreeing to pay the ransom Mullen then decides to use the ransom money as a bounty.",
                "https://www.movieposters.com/cdn/shop/products/8ac813465252934d49309afc97436271_480x.progressive.jpg?v=1573572636",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 50,
                releaseDate = "15/01/2023"
            ),
            MovieModel(
                "8",
                "Fighting Mad",
                "",
                "https://www.movieposters.com/cdn/shop/products/scan009_902cecb0-772b-46d3-9200-8425a4eb1c78_480x.progressive.jpg?v=1676483742",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 200,
                releaseDate = "15/02/2023"
            ),
            MovieModel(
                "9",
                "Wild Robot",
                "After a shipwreck, an intelligent robot called Roz is stranded on an uninhabited island. To survive the harsh environment, Roz bonds with the island's animals and cares for an orphaned baby goose.",
                "https://www.movieposters.com/cdn/shop/files/wild_robot_ver2_480x.progressive.jpg?v=1718830597",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 50,
                releaseDate = "11/01/2023"
            ),
            MovieModel(
                "10",
                "Book of Boba Fett",
                "Bounty hunter Boba Fett and mercenary Fennec Shand navigate the underworld when they return to Tatooine to claim Jabba the Hutt's old turf.",
                "https://www.movieposters.com/cdn/shop/products/the-book-of-boba-fett_ajhlb82g_480x.progressive.jpg?v=1660670796",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 120,
                releaseDate = "11/01/2022"
            ),
            MovieModel(
                "11",
                "Secret Invasion",
                "Fury and Talos try to stop the Skrulls who have infiltrated the highest spheres of the Marvel Universe.",
                "https://www.movieposters.com/cdn/shop/files/secret-invasion_6aakqads_480x.progressive.jpg?v=1694184698",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 10,
                releaseDate = "11/09/2021"
            ),
            MovieModel(
                "12",
                "Spider-Man: No Way Home",
                "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.",
                "https://www.movieposters.com/cdn/shop/files/scan003_8262b28b-4c2f-4a6d-93d9-3c46f3dd484c_480x.progressive.jpg?v=1700084994",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 50,
                releaseDate = "19/01/2023"
            ),
            MovieModel(
                "13",
                "Creator",
                "Against the backdrop of a war between humans and robots with artificial intelligence, a former soldier finds the secret weapon, a robot in the form of a young child.",
                "https://www.movieposters.com/cdn/shop/files/scan008_0410983d-03f0-46f2-9de3-9a84ea7c2966_480x.progressive.jpg?v=1694461137",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 2,
                releaseDate = "19/12/2021"
            ),
            MovieModel(
                "14",
                "Sherlock Holmes",
                "Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England.",
                "https://www.movieposters.com/cdn/shop/products/1242d25878b9c449027f86a40f376b3c_c50ff475-6079-4d1a-9200-a5f89c9bcff5_480x.progressive.jpg?v=1573618731",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 11,
                releaseDate = "15/11/2021"
            ),
            MovieModel(
                "15",
                "Due Date",
                "High-strung father-to-be Peter Highman is forced to hitch a ride with aspiring actor Ethan Tremblay on a road trip in order to make it to his child's birth on time.",
                "https://www.movieposters.com/cdn/shop/products/d3994eb59aca28e452905e3efdb7048c_480x.progressive.jpg?v=1573652710",
                initialFavorite = true, rating = "8/10", comment = "200k", voting = 5,
                releaseDate = "15/10/2021"
            )
        )
    }

    override suspend fun searchMovie(value: String): List<MovieModel> {
        return getMovies()
            .filter {
                it.name.contains(value, true)
            }
    }
}
