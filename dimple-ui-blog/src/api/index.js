import article from "./article";
import comments from "./comment";
// import messageBoard from './messageBoard'
import category from "./category";
import tags from "./tag";
import archives from "./archives";

export default {
    ...article,
    ...archives,
    ...comments,
    ...category,
    ...tags,
}
