package br.ufu.sisegresso.model

import br.ufu.sisegresso.exception.InconsistentRecordException
import br.ufu.sisegresso.exception.ServiceInternalError
import br.ufu.sisegresso.messages.AppMessages
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
@Table(name = "token")
class Token (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @NotNull
    var token: String? = "",

    var dataExpiracao: LocalDateTime? = LocalDateTime.now(),

    @Transient
    var expirado: Boolean? = null,

    @OneToOne
    @JoinColumn(name = "egresso_id", referencedColumnName = "id")
    var egresso: Egresso? = null,
) {
    @PostLoad
    fun postLoad() {
        val currentDateTime = LocalDateTime.now()
            ?.atOffset(ZoneOffset.UTC)
            ?.toLocalDateTime()

        if (currentDateTime == null) {
            throw ServiceInternalError(AppMessages.LOCAL_DATE_TIME_ERROR.name)
        }

        if (dataExpiracao == null) {
            throw InconsistentRecordException(AppMessages.INCONSISTENT_RECORD.name)
        }

        if (dataExpiracao != null) {
            expirado = dataExpiracao!!.isBefore(currentDateTime)
        } else {
            expirado = dataExpiracao?.isBefore(currentDateTime)
        }
    }
}