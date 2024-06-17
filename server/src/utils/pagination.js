export const pagingConfig = (page, pageSize, orderBy, orderDirection) => {
    const queries = { raw: true, nest: true };
    const offset = !page || +page <= 1 ? 0 : +page - 1;
    const limit = +pageSize || +process.env.LIMIT_ITEM;
    queries.orderBy = orderBy ? orderBy : 'createdAt';
    queries.orderDirection = orderDirection ? orderDirection : 'DESC';
    queries.page = queries.offset = offset;
    queries.limit = limit;
    queries.order = [[queries.orderBy, queries.orderDirection]];
    return queries;
};
