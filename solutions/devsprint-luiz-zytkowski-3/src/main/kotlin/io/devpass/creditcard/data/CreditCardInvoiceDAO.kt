package io.devpass.creditcard.data


import io.devpass.creditcard.data.repositories.CreditCardInvoiceRepository
import io.devpass.creditcard.dataaccess.ICreditCardInvoiceDAO
import io.devpass.creditcard.domain.objects.CreditCardInvoice
import io.devpass.creditcard.domain.objects.CreditCardInvoiceByDate

class CreditCardInvoiceDAO(
    private val creditCardInvoiceRepository: CreditCardInvoiceRepository,
) : ICreditCardInvoiceDAO {
    override fun getById(id: String): CreditCardInvoice? {
        val creditCardInvoiceEntity = creditCardInvoiceRepository.findById(id)
        return if (creditCardInvoiceEntity.isPresent) creditCardInvoiceEntity.get()
            .toCreditCardInvoice() else null
    }

    override fun findInvoiceByDate(creditCardInvoiceByDate: CreditCardInvoiceByDate): CreditCardInvoice? {
        return creditCardInvoiceRepository.findByInvoiceByDate(
            creditCardInvoiceByDate.creditCard,
            creditCardInvoiceByDate.month,
            creditCardInvoiceByDate.year
        ).firstOrNull()?.toCreditCardInvoice()

    }

    override fun generateCreditCardInvoice(creditCardId: String, invoiceMonth: Int, invoiceYear: Int): CreditCardInvoice {
        return creditCardInvoiceRepository.getInvoice(creditCardId, invoiceMonth, invoiceYear)
    }
}