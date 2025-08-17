using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public interface ISubcategoryRepository
    {
        Task<IEnumerable<Subcategory>> GetAllAsync();
        Task<IEnumerable<Subcategory>> GetByCategoryIdAsync(int categoryId);
    }
}
