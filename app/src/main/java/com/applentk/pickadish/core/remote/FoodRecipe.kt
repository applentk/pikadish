package com.applentk.pickadish.core.remote

data class FoodRecipe(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val imageUrl: String?,
    val youtubeUrl: String?,
    val ingredients: List<Pair<String, String>>
) {
    companion object {
        fun fromDto(dto: FoodRecipeDto): FoodRecipe {
            val ingredientsList = mutableListOf<Pair<String, String>>()

            // Iterate through the 20 possible ingredient/measure pairs
            for (i in 1..20) {
                val ingredient = when (i) {
                    1 -> dto.strIngredient1 2 -> dto.strIngredient2 3 -> dto.strIngredient3
                    4 -> dto.strIngredient4 5 -> dto.strIngredient5 6 -> dto.strIngredient6
                    7 -> dto.strIngredient7 8 -> dto.strIngredient8 9 -> dto.strIngredient9
                    10 -> dto.strIngredient10 11 -> dto.strIngredient11 12 -> dto.strIngredient12
                    13 -> dto.strIngredient13 14 -> dto.strIngredient14 15 -> dto.strIngredient15
                    16 -> dto.strIngredient16 17 -> dto.strIngredient17 18 -> dto.strIngredient18
                    19 -> dto.strIngredient19 20 -> dto.strIngredient20
                    else -> null
                }
                val measure = when (i) {
                    1 -> dto.strMeasure1 2 -> dto.strMeasure2 3 -> dto.strMeasure3
                    4 -> dto.strMeasure4 5 -> dto.strMeasure5 6 -> dto.strMeasure6
                    7 -> dto.strMeasure7 8 -> dto.strMeasure8 9 -> dto.strMeasure9
                    10 -> dto.strMeasure10 11 -> dto.strMeasure11 12 -> dto.strMeasure12
                    13 -> dto.strMeasure13 14 -> dto.strMeasure14 15 -> dto.strMeasure15
                    16 -> dto.strMeasure16 17 -> dto.strMeasure17 18 -> dto.strMeasure18
                    19 -> dto.strMeasure19 20 -> dto.strMeasure20
                    else -> null // Safely handle omitted large block
                }

                if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) {
                    ingredientsList.add(Pair(ingredient.trim(), measure.trim()))
                }
            }

            return FoodRecipe(
                id = dto.idMeal ?: "Unknown ID",
                name = dto.strMeal ?: "Unnamed Recipe",
                category = dto.strCategory ?: "Unknown Category",
                area = dto.strArea ?: "Unknown Area",
                instructions = dto.strInstructions ?: "No instructions provided.",
                imageUrl = dto.strMealThumb,
                youtubeUrl = dto.strYoutube,
                ingredients = ingredientsList
            )
        }
    }
}