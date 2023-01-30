export default interface Repository<T> {
    find: (input : T) => Promise<T>,
    findAll: (input?: T) => Promise<T[]>,
    create?: (input: T[]) => Promise<Boolean>
};