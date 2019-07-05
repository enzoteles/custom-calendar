package com.example.calendarcustom.simulation

data class SimulationResponse(
    val installment: Installment,
    val token: String
)

data class Installment(
    val amount: Int,
    val installmentAmount: Double,
    val installments: Int,
    val iof: Double,
    val iofRate: Double,
    val monthlyInterestRate: Double,
    val totalAmount: Double
)