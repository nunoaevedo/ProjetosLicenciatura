package com.example.mymuseum.Item

class ItemSource{

    companion object{
        fun createDataSetA() : ArrayList<ItemData>{
            var list = ArrayList<ItemData>()

            list.add(
                ItemData(
                    "Item A",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemAMuseuA.png"

                )
            )
            list.add(
                ItemData(
                    "Item B",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemBMuseuA.png"

                )
            )
            list.add(
                ItemData(
                    "Item C",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemCMuseuA.png"

                )
            )
            list.add(
                ItemData(
                    "Item D",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemDMuseuA.png"

                )
            )

            return list

        }

        //CRIAR DATA SET PARA O MUSEU B
        fun createDataSetB() : ArrayList<ItemData>{
            var list = ArrayList<ItemData>()

            list.add(
                ItemData(
                    "Item A",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemAMuseuB.png"

                )
            )
            list.add(
                ItemData(
                    "Item B",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemBMuseuB.png"

                )
            )
            list.add(
                ItemData(
                    "Item C",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemCMuseuB.png"

                )
            )
            list.add(
                ItemData(
                    "Item D",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemDMuseuB.png"

                )
            )

            return list
        }


        fun createDataSetC() : ArrayList<ItemData>{
            var list = ArrayList<ItemData>()

            list.add(
                ItemData(
                    "Item A",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemAMuseuC.png"

                )
            )
            list.add(
                ItemData(
                    "Item B",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemBMuseuC.PNG"

                )
            )
            list.add(
                ItemData(
                    "Item C",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemCMuseuC.PNG"

                )
            )
            list.add(
                ItemData(
                    "Item D",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemDMuseuC.PNG"

                )
            )

            return list



        }


        fun createDataSetD() : ArrayList<ItemData>{
            var list = ArrayList<ItemData>()

            list.add(
                ItemData(
                    "Item A",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemAMuseuD.png"

                )
            )
            list.add(
                ItemData(
                    "Item B",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemBMuseuD.PNG"

                )
            )
            list.add(
                ItemData(
                    "Item C",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemCMuseuD.PNG"

                )
            )
            list.add(
                ItemData(
                    "Item D",
                    "https://raw.githubusercontent.com/nunoaevedo/imagens/master/ImagemDMuseuD.PNG"

                )
            )

            return list


        }


    }
}