package com.example.mymuseum.Museu

import android.net.Uri
import com.example.mymuseum.Item.ItemSource

class MuseuSource {

    companion object{

        fun createDataSet(): ArrayList<MuseuData>{

            val list = ArrayList<MuseuData>()

            list.add(
                MuseuData(
                    "Museu Calouste Gulbenkian",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/MuseuA.png",
                    ItemSource.createDataSetA(),
                    "geo:0.0?q=Museu Calouste Gulbenkian, Avenida de Berna, Lisboa",
                    "Quarta-feira a Segunda-Feira das 10:00 às 18:00",
                    "https://gulbenkian.pt/museu/",

                    "A Fundação Calouste Gulbenkian foi criada em 1956 por testamento de Calouste Sarkis Gulbenkian, filantropo de"+
                            "origem Arménia que viveu em Lisboa entre 1942 e 1955, ano em que faleceu.\n\n"+
                            "De nacionalidade Portuguesa e instituída em perpetuidade, a Fundação tem como propósito fundamental melhorar a qualidade"+
                            "de vida das pessoas através da arte, da beneficência, da ciência e da educação. A Fundação desenvolve as suas atividades"+
                            "a partir da sua sede em Lisboa e das delegações em Paris e em Londres, tendo também intervenção através de apoios" +
                            "concedidos desde Portugal nos PALOP e Timor-Leste bem como nos países com Comunidades Arménias.\n\n" +
                            "A Fundação conta com um museu, que alberga a coleção particular do Fundador e uma coleção de arte moderna e contemporânea;" +
                            "uma orquestra e um coro; uma biblioteca de arte e arquivo; um instituto de investigação científica; e um jardim, que é um" +
                            "espaço central da cidade de Lisboa, onde decorrem também as atividades educativas."

                )
            )
            list.add(
                MuseuData(
                    "Museu Nacional de História Natural e da Ciência",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/MuseuB.png",
                    ItemSource.createDataSetB(),
                    "geo:0.0?q=Museu Nacional de História Natural e da Ciência, Rua da Escola Politécnica, Lisboa",
                    "Terça a sexta-feira: 10h00 às 17h00 - Sábados e domingos - 11h00 às 18h00",
                    "https://www.museus.ulisboa.pt/",

                    "O MUHNAC - Museu Nacional de História Natural e da Ciência é a designação pública da unidade Museus da Universidade de Lisboa, criada em outubro de 2011. " +
                            "Este Museu sucede ao Museu Nacional de História Natural e ao Museu de Ciência da Universidade de Lisboa, integrando as suas coleções, os antigos edifícios da Escola Politécnica, o " +
                            "Jardim Botânico de Lisboa e o Observatório Astronómico de Lisboa (desde julho de 2012).\n"
                        +
                    "O Museu Nacional de História Natural teve a sua origem no Real Museu de História Natural e Jardim Botânico, criado na segunda metade do século XVIII, na Ajuda (Lisboa). " +
                            "Foi depois alojado, por um curto espaço de tempo, na Real Academia das Ciências e finalmente transferido para a Escola Politécnica (1858), tomando primeiro a designação de Museu Nacional de " +
                            "Lisboa (1861)."

                )
            )
            list.add(
                MuseuData(
                    "Museu Nacional dos Coches",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/MuseuC.png",
                    ItemSource.createDataSetC(),
                    "geo:0.0?q=Museu Nacional dos Coches, Avenida da Índia, Lisboa",
                    "Todos os dia das 10:00 às 13:00 e das 14:30 às 17:00",
                    "http://museudoscoches.gov.pt/pt/",
                    "O novo Museu dos Coches surge em Belém como um equipamento cultural mas também como um lugar público. Nas palavras do arquiteto Paulo Mendes da Rocha “o museu não tem porta e relaciona-se para todos" +
                            " os lados”. Mais que um museu, o projeto funciona como uma infraestrutura urbana, que oferece ‘espaço público’ à cidade."+

                    "Convergiram assim duas preocupações, por um lado a necessidade imperativa de aumentar a área expositiva do museu assim como a sua infraestrutura técnica de apoio, mas também a indispensabilidade de criar" +
                            " novas valências para o público daquele que é o museu mais visitado do país. Por outro lado, contou a necessidade de realizar o remate daquela que é uma das mais importantes frentes urbanas de" +
                            " Lisboa, a zona monumental de Belém, tendo a construção do novo edifício incentivado uma nova dinâmica do território envolvente ao museu, criando novos espaços públicos e percursos na cidade que " +
                            "evocam anteriores vivências."+

                    "O novo edifício do Museu dos Coches é constituído por um pavilhão principal com uma nave suspensa e um anexo, com uma ligação aérea, que assegura a circulação entre os dois edifícios. A disposição espacial " +
                            "destes corpos cria uma espécie de pórtico que aponta para uma praça interna, para onde também se viram as construções antigas da Rua da Junqueira."
                )
            )
            list.add(
                MuseuData(
                    "Museu de São Roque",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/MuseuD.png",
                    ItemSource.createDataSetD(),
                    "geo:0.0?q=Museu de São Roque, Largo Trindade Coelho, Lisboa",
                    "Terça-feira a domingo – 10h00 às 18h00",
                    "https://mais.scml.pt/museu-saoroque/",

                    "O Museu de São Roque foi um dos primeiros museus de arte a serem criados em Portugal. Abriu ao público em 11 de Janeiro de 1905, com a designação de Museu do Thesouro da Capela de São " +
                            "João Baptista, em evocação da importante coleção de arte italiana que esteve na origem da sua criação. Desde a sua abertura ficou instalado no edifício da antiga Casa-Professa da Companhia de " +
                            "Jesus em Lisboa, espaço contíguo à Igreja de São Roque, que tinha sido doado à Santa Casa da Misericórdia de Lisboa em 1768, após a expulsão dos jesuítas." +

                    "Ao longo do século XX foi objeto de várias remodelações ,que permitiram acompanhar as mudanças operadas no domínio da museologia. A remodelação mais profunda foi levada a cabo entre 2006 e 2008," +
                            " permitindo ao museu ampliar e duplicar a sua área de exposição permanente."
                )
            )

            return list



        }



    }


}