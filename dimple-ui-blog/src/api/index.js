import article from "./article";
import comments from "./comment";
import messageBoard from './messageBoard'
import category from "./category";
import tags from "./tag";
import archives from "./archives";
import link from "./link";
import visitor from "./visitor";
import panel from "./panel";
import about from "./about";
import asyncData from "./asyncData";

export default {
  ...article,
  ...archives,
  ...comments,
  ...category,
  ...messageBoard,
  ...link,
  ...about,
  ...panel,
  ...visitor,
  ...tags,
  ...asyncData
}
