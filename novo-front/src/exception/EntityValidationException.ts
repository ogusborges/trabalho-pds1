import type { ValidationError } from '@/service/RequestResponse'

export class EntityValidationException extends Error {
  private validationErrors: ValidationError[]

  constructor(message: string, validationErrors: ValidationError[]) {
    super(message)
    this.validationErrors = validationErrors
  }

  getValidationErrors = () => this.validationErrors
}
